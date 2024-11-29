package main

import (
	"encoding/json"
	"errors"
	"golang.org/x/exp/rand"
	"log"
	"net/http"
	"strconv"
	"time"
)

type TodoItem struct {
	Name        string    `json:"name"`
	Description string    `json:"description"`
	Timestamp   time.Time `json:"timestamp"`
}

var (
	todoItemListDB = []TodoItem{
		{"Finish Lunch", "Finish today's lunch", time.Now().Add(1 * time.Hour).UTC().Truncate(time.Second)},
		{"Finish Dinner", "Finish today's dinner", time.Now().Add(2 * time.Hour).UTC().Truncate(time.Second)},
		{"Finish Homework", "Finish today's Homework", time.Now().Add(3 * time.Hour).UTC().Truncate(time.Second)},
		{"Finish Project", "Finish today's project", time.Now().Add(4 * time.Hour).UTC().Truncate(time.Second)},
	}
)

var (
	todoItemListCache = map[int]TodoItem{}
)

func main() {
	http.HandleFunc("/v1/todo", func(w http.ResponseWriter, r *http.Request) {
		index := chooseRandomIndex(len(todoItemListDB))
		value, err := getCache(index)
		if err != nil {
			value = todoItemListDB[index]
			setCache(index, value)
		}
		json.NewEncoder(w).Encode(value)
	})

	http.HandleFunc("/v1/todo-item", func(w http.ResponseWriter, r *http.Request) {
		index, _ := strconv.ParseInt(r.URL.Query()["index"][0], 10, 64)
		value, err := getCache(index)
		if err != nil {
			value = todoItemListDB[index]
			setCache(index, value)
		}
		json.NewEncoder(w).Encode(value)
	})
	http.ListenAndServe(":8080", nil)
}

func chooseRandomIndex(size int) int {
	rand.Seed(uint64(time.Now().Nanosecond()))
	return rand.Intn(size)
}

func setCache(key interface{}, value interface{}) {
	if len(todoItemListCache) > 2 {
		// deleting key from cache, random key deletion
		for k, _ := range todoItemListCache {
			delete(todoItemListCache, k)
			break
		}
	}
	todoItemListCache[key.(int)] = value.(TodoItem)
}

func getCache(key interface{}) (interface{}, error) {
	value, ok := todoItemListCache[key.(int)]
	if ok {
		log.Println("Fetched data from Cache")
		return value, nil
	}
	return nil, errors.New("Not Found")
}
