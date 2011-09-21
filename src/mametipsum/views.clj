(ns mametipsum.views
  (:use
   [hiccup core page-helpers])
  (:import
   java.util.Date
   java.text.SimpleDateFormat))

(defn mametipsum-index-view []
  (html5
   [:head
    [:title "mametipsum"]
    (include-css "/css/site.css")]
   [:body
    [:h1 "Always Be Closing!"]
    [:p
     (str
      "The current time is "
      (.format (SimpleDateFormat. "HH:mm:ss") (Date.)) ".")]]))
