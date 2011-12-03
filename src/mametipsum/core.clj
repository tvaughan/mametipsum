(ns mametipsum.core
  (:require
   [ring.adapter.jetty :as jetty]
   [compojure.handler :as handler]
   [mametipsum.db :as db]
   [mametipsum.routes :as routes])
  (:use
   [ring.middleware.format-params :only (wrap-restful-params)]
   [ring.middleware.format-response :only (wrap-restful-response)]
   [hiccup.middleware :only (wrap-base-url)]))

(def app
  (wrap-restful-params
   (wrap-restful-response
    (wrap-base-url (handler/site routes/main-routes)))))

(defn -main []
  (db/init)
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (jetty/run-jetty app {:port port})))
