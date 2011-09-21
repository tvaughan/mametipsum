(ns mametipsum.web
  (:use ring.adapter.jetty)
  (:import java.util.Date java.text.SimpleDateFormat))

(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (str "Put that coffee down! "
              "The current time is "
              (.format (SimpleDateFormat. "HH:mm:ss") (Date.)) ".")})

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port})))
