(ns dk.salza.emacs-jack-in
  "Experiment with integration into Emacs
  This will only work if Emacs is installed and on path.

  # To enable
  Add 

    {:deps mogenslund/emacs-jack-in
           {:git/url \"https://github.com/mogenslund/emacs-jack-in.git\"
            :tag \"v0.1.1\"}}

  to deps.edn

  Require [dk.salza.emacs-jack-in :as emacs-jack-in]

  Eval:

    (editor/set-global-key \"f8\" dk.salza.emacs-jack-in/forward-sexp)

  To define f8 as forward-sexp function globally"
  (:require [dk.salza.liq.editor :as editor]
            [dk.salza.liq.slider :refer :all]
            [dk.salza.liq.tools.cshell :refer :all]
            [clojure.string :as str]))

(defn escape-chars
  [s]
  (-> s
    (str/replace #"\\" (str "\\\\" "u005C"))
    (str/replace #"\"" (str "\\\\" "u0022")))) ;"

(defn emacs-cmd
  [sl command-str]
  (let [out (cmd "emacs" "-batch" "--eval"
                 (str "(progn "
                       "(get-buffer-create \" buf\") "
                       "(insert \"" (escape-chars (get-content sl)) "\") "
                       "(goto-char " (+ (get-point sl) 1) ") "
                       command-str " "
                     "(message \"\n--------------%S\n\" (point)) "
                     "(message \"%s\" (buffer-string)))"))
          p (Integer/parseInt (re-find #"(?<=--------------)\d+" out))
          sl1 (create (second (str/split out #"--------------\d+\n\n")))]
      (set-point sl1 (- p 1))))

(defn forward-sexp [sl] (emacs-cmd sl "(forward-sexp)"))
