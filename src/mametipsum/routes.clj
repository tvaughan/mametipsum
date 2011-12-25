(ns mametipsum.routes
  (:require
   [compojure.route :as route]
   [mametipsum.views :as views])
  (:use
   [compojure.core]))

(defroutes main-routes
  (GET "/mametipsum" []
       (views/list-titles))
  ;; C - Create
  (POST "/mametipsum/:title" [title params]
        (views/create-script title params))
  ;; R - Read
  (GET "/mametipsum/:title/:nblocks/:nwords" [title nblocks nwords]
       (views/read-script title (Integer/parseInt nblocks) (Integer/parseInt nwords)))
  ;; U - Update
  (PUT "/mametipsum/:title" [title params]
       (views/update-script title params))
  ;; D - Delete
  (DELETE "/mametipsum/:title" [title]
          (views/delete-script title))
  (route/resources "/")
  (route/not-found "404"))
