(ns mametipsum.views
  (:import
   java.util.Date
   java.text.SimpleDateFormat)
  (:require
   [hiccup.page-helpers :as page-helpers]))

(defn home []
  (page-helpers/html5
   [:head
    [:title "mametipsum"]
    (page-helpers/include-css "/css/site.css")]
   [:body
    [:h1 "Always Be Closing!"]
    [:p
     (str
      "The current time is "
      (.format (SimpleDateFormat. "HH:mm:ss") (Date.)) ".")]]))
