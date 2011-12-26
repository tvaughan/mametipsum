(ns mametipsum.views
  (:require
   [mametipsum.scripts :as scripts]))

(defn- with-handle [f & args]
  (let [handle (scripts/get-handle)]
    (apply f handle args)))

(defn list-titles []
  (with-handle scripts/list-titles))

(defn create-script [title params]
  (with-handle scripts/create-script title params))

(defn read-script [title nblocks nwords]
  (with-handle scripts/read-script title nblocks nwords))

(defn update-script [title params]
  (with-handle scripts/update-script title params))

(defn delete-script [title]
  (with-handle scripts/delete-script title))
