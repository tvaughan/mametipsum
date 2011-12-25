(ns mametipsum.views
  (:require
   [mametipsum.db :as db]))

(defn list-titles []
  (db/list-titles (db/get-scripts)))

(defn create-script [title params]
  (db/create-script (db/get-scripts) title params))

(defn read-script [title nblocks nwords]
  (db/read-script (db/get-scripts) title nblocks nwords))

(defn update-script [title params]
  (db/update-script (db/get-scripts) title params))

(defn delete-script [title]
  (db/delete-script (db/get-scripts) title))
