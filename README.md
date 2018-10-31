# emacs-jack-in
Experimental extensions for Liquid to take advantage of elisp through Emacs

## To install
The [liquid-starter-kit](https://github.com/mogenslund/liquid-starter-kit) can be used as example and in general as a way to setup Liquid locally.

In the deps.edn include this project in the deps section, like

    {:deps mogenslund/emacs-jack-in {:git/url "https://github.com/mogenslund/emacs-jack-in.git"
                                     :tag "v0.1.1"}}

(Maybe with an updated tag, to get the newest version)

In your project require `[dk.salza.emacs-jack-in :as emacs-jack-in]` and assign a keyboad shortcut like this:

    (editor/set-global-key "f8" emacs-jack-in/forward-sexp)

