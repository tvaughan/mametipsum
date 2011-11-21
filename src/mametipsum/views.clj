(ns mametipsum.views
  (:require
   [hiccup.form-helpers :as form-helpers]
   [hiccup.page-helpers :as page-helpers]
   [mametipsum.db :as db]))

(defn- list-script-titles []
  (for [script (db/list-scripts)] (script :title)))

(defn home []
  (page-helpers/html5
   [:head
    [:title "mametipsum"]
    (page-helpers/include-css "/css/site.css")]
   [:body
    (form-helpers/form-to [:get "mametipsum"]
                          [:p
                           (form-helpers/drop-down :title (list-script-titles))]
                          [:p
                           (form-helpers/label :nwords "Number of Words")
                           (form-helpers/text-field :nwords)]
                          [:p
                           (form-helpers/submit-button "OK")])]))

(defn list-scripts []
  {:status 501})

(defn create-script [script]
  {:status 501})

(defn read-script [script]
  {:status 501})

(defn update-script [script]
  {:status 501})

(defn delete-script [script]
  {:status 501})
