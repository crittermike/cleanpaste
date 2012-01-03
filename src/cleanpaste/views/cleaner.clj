(ns cleanpaste.views.cleaner
  (:require [cleanpaste.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage defpartial render]]
        [hiccup.form-helpers :only [form-to submit-button text-area]]
        [hiccup.core :only [html]]))

(defn clean-text [text]
  (clojure.string/replace text #"[^\u0000-\u007F]" ""))

(defpartial cleaner-form [& paste]
  (text-area "paste" (first paste))
  (submit-button "Clean it up!"))

(defpage "/" []
  (common/layout 
    (form-to [:post "/"]
             (cleaner-form))))

(defpage [:post "/"] {paste :paste}
  (common/layout 
    (form-to [:post "/"]
             (cleaner-form (clean-text paste)))))

