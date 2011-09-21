(ns mametipsum.routes
  (:use compojure.core)
  (:require [compojure.route :as route])
  (:import java.util.Date java.text.SimpleDateFormat))

(defroutes mametipsum-routes
  (GET "/" [] (str
               "<h2>Put that coffee down!</h2> The current time is "
               (.format (SimpleDateFormat. "HH:mm:ss") (Date.)) "."))
  (route/resources "/")
  (route/not-found "404"))
