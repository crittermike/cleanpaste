(ns cleanpaste.views.cleaner
  (:require [cleanpaste.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage defpartial render]]
        [hiccup.form-helpers :only [form-to submit-button text-area]]
        [hiccup.core :only [html]]))

(defn clean-text [text]
  (if (string? text)
      (clojure.string/replace text #"[^\u0000-\u007F]" "")
      text))

(defpartial cleaner-form [{:keys [paste]}]
  (text-area "paste" (clean-text paste))
  (submit-button "Clean it up!"))

(defpage "/" {:as result}
  (common/layout 
    [:p "Remove all non-ASCII characters from text."]
    (form-to [:post "/"]
             (cleaner-form result))))

(defpage [:post "/"] {:as result}
  (render "/" result))
