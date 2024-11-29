package main

import (
	"encoding/json"
	"net/http"
	"time"

	"golang.org/x/exp/rand"
)

var (
	shortURLPrefix = "www.tinyurl.com/"
)

var (
	globalMapShortURLToLongURL = map[string]string{}
	globalMapLongURLToShortURL = map[string]string{}
)

func main() {
	http.HandleFunc("/short", func(w http.ResponseWriter, r *http.Request) {
		longURL := r.URL.Query()["url"][0]
		shortURL, ok := globalMapLongURLToShortURL[longURL]
		if !ok {
			shortURL = shortURLPrefix + generateShortKey()
			globalMapLongURLToShortURL[longURL] = shortURL
			globalMapShortURLToLongURL[shortURL] = longURL
		}
		json.NewEncoder(w).Encode(shortURL)
	})
	http.HandleFunc("/long", func(w http.ResponseWriter, r *http.Request) {
		shortURL := r.URL.Query()["url"][0]
		longURL, ok := globalMapShortURLToLongURL[shortURL]
		if !ok {
			http.Error(w, "longURL not exist", http.StatusInternalServerError)
			return
		}
		json.NewEncoder(w).Encode(longURL)
	})
	http.ListenAndServe(":8080", nil)
}

func generateShortKey() string {
	const charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
	const keyLength = 6

	rand.Seed(uint64(time.Now().UnixNano()))
	shortKey := make([]byte, keyLength)
	for i := range shortKey {
		shortKey[i] = charset[rand.Intn(len(charset))]
	}
	return string(shortKey)
}
