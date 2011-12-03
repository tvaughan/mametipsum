(ns mametipsum.views
  (:require
   [hiccup.form-helpers :as form-helpers]
   [hiccup.page-helpers :as page-helpers]
   [mametipsum.db :as db]))

(defn- list-script-titles []
  (for [script (db/list-scripts)] (script :title)))

(defn- form []
  (form-helpers/form-to {:class "form-stacked" :id "mametipsum-form"} [:post ""]
                        [:fieldset
                         [:div {:class "clearfix"}
                          (form-helpers/label :title "Take Words From")
                          (form-helpers/drop-down :title (list-script-titles))]
                         [:div {:class "clearfix"}
                          (form-helpers/label :nwords "Total Number of Words")
                          (form-helpers/text-field {:placeholder 0} :nwords)]
                         [:div {:class "actions"}
                          (form-helpers/submit-button {:class "btn"} "Let's Do This")]]))

(defn home []
  (page-helpers/html5
   [:head
    [:title "mametipsum"]
    (page-helpers/include-css "http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css")
    (page-helpers/include-js "http://code.jquery.com/jquery-1.7.min.js")
    (page-helpers/include-js "/js/site.js")]
   [:body
    [:div {:class "container"}
     [:div {:class "row"}
      [:div {:class "span5"}
       (form)]]]]))

(defn list-scripts []
  (list-script-titles))

(defn create-script [script]
  {:status 501})

(defn read-script [script nwords]
  {:status 200})

(defn update-script [script]
  {:status 501})

(defn delete-script [script]
  {:status 501})
