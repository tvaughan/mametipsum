(ns mametipsum.db)

(defstruct data :line :length)

(defn parse [file-name]
  (with-open [fd (clojure.java.io/reader file-name)]
    (let [lines (line-seq fd)]
      (doall (map (fn [line length]
                    (struct-map data line length))
                  lines (map count lines))))))

(defn remove-directories [list]
  (remove #(.isDirectory %) list))

(defn init-db []
  (for [file-name (remove-directories
                   (file-seq (clojure.java.io/file "db")))]
    (parse file-name)))
