(ns cleanpaste.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "Clean Paste"]
               (include-css "/css/style.css")]
              [:body
               [:div#wrapper
                [:h1 "Clean Paste"]
                content]]))
