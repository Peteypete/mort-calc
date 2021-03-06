(defproject mort-calc "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [reagent "0.6.0"]
                 [com.andrewmcveigh/cljs-time "0.4.0"]
                 [re-frame "0.8.0"]]

  :plugins [[lein-cljsbuild "1.1.4"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.8.2"]]

    :plugins      [[lein-figwheel "0.5.7"] [lein-externs "0.1.6"]]}}


  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "mort-calc.core/mount-root"}
     :compiler     {:main                 mort-calc.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}


    {:id           "prod"
     :source-paths ["src/cljs"]
     :compiler     {:main            mort-calc.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations :advanced
                    :closure-defines {goog.DEBUG false}
                    :externs ["resources/externs.js"]
                    :pretty-print    false}}]})
