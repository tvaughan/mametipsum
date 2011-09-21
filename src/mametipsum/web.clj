(ns mametipsum.web
  (:use ring.adapter.jetty)
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler])
  (:import java.util.Date java.text.SimpleDateFormat))

(defroutes mametipsum-routes
  (GET "/" [] (str
               "<h2>Put that coffee down!</h2> The current time is "
               (.format (SimpleDateFormat. "HH:mm:ss") (Date.)) "."))
  (route/resources "/")
  (route/not-found "404"))

(def app
  (handler/site mametipsum-routes))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port})))
