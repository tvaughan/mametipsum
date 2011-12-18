(ns mametipsum.routes
  (:require
   [compojure.route :as route]
   [mametipsum.views :as views])
  (:use
   [compojure.core]))

(defroutes main-routes
  (GET "/mametipsum" []
       (views/list-titles))
  (POST "/mametipsum/:title" [title params]
        (views/create-script title params))
  (GET "/mametipsum/:title/:nwords" [title nwords]
       (views/read-script title nwords))
  (PUT "/mametipsum/:title" [title params]
       (views/update-script title params))
  (DELETE "/mametipsum/:title" [title]
          (views/delete-script title))
  (route/resources "/")
  (route/not-found "404"))
