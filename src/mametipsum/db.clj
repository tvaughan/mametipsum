(ns mametipsum.db)

(defn- remove-directories [list]
  (remove #(.isDirectory %) list))

(defn- get-blobs []
  (remove-directories (file-seq (clojure.java.io/file "db"))))

(defstruct line :string :nwords)

(defn- parse-blob [strings]
  (doall
   (map (fn [string nwords]
          (struct line string nwords))
        strings (map count strings))))

(defn- add-blob [filename]
  (with-open [fd (clojure.java.io/reader filename)]
    (parse-blob (line-seq fd))))

(def *scripts* (ref {}))

(defn get-scripts []
  *scripts*)

(defn list-titles [scripts]
  (keys @scripts))

(defn- print-titles [scripts]
  (doseq [title (list-titles scripts)]
    (println title)))

(defn list-values [scripts]
  (vals @scripts))

(defn- print-values [scripts]
  (doseq [value (list-values scripts)]
    (println value)))

(defn create-script [scripts title data]
  (dosync (alter scripts assoc title data)))

(defn- iter-script [script nwords]
  (loop [i 0 current (first script) remainder (rest script) lines []]
    (if (< i nwords)
      (let [total (+ i (current :nwords))]
        (recur total (first remainder) (rest remainder) (conj lines (current :string))))
      lines)))

(defn read-script [scripts title nblocks nwords]
  (loop [i 0 script (scripts title) blocks []]
    (if (< i nblocks)
      (let [total (inc i)]
        (recur total script (cons (iter-script script nwords) blocks)))
      blocks)))

(defn update-script [scripts title data]
  ;; TODO:
  )

(defn delete-script [scripts title]
  (dosync (alter scripts dissoc title)))

(defn init []
  (let [scripts (get-scripts)]
    (doseq [blob (get-blobs)]
      (create-script scripts (.getName blob) (add-blob blob)))))
