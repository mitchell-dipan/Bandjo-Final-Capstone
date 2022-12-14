<template>
  <div>
    <ul
      v-for="genre in genres"
      :key="genre.genreId"
      @click="selectGenre(genre)"
    >
      <li>{{ genre.genreName }}</li>
    </ul>
    <div>
      <h2>Genres To Delete</h2>
      <ul
        v-for="genre in chosenGenres"
        :key="genre.genreId"
        @click="unselectGenre(genre)"
      >
        <li>{{ genre.genreName }}</li>
      </ul>
    </div>
    <button @click="deleteGenres">Delete</button>
    <div>
      <h2>Add Genre</h2>
      <input type="text" v-model="newGenre" />
      <button @click="addGenre()">Add</button>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService";
export default {
  data() {
    return {
      genres: [],
      chosenGenres: [],
      newGenre: "",
    };
  },
  created() {
    MessageService.getAllGenres().then((r) => {
      this.genres = r.data;
    });
  },
  methods: {
    selectGenre(genre) {
      this.chosenGenres.push(genre);
    },
    unselectGenre(genre) {
      const index = this.chosenGenres.indexOf(genre);
      this.chosenGenres.splice(index, 1);
    },
    deleteGenres() {
      this.chosenGenres.forEach((genre) => {
        MessageService.deleteGenres(genre.genreName).then();
        const index = this.genres.indexOf(genre);
        this.genres.splice(index, 1);
      });
      this.chosenGenres = [];
    },
    addGenre() {
      let newName = this.newGenre;
      let bool = false;
      if (newName == "") {
        bool = true;
      } else {
        this.genres.forEach((g) => {
          if (g.genreName.toLowerCase() == newName.toLowerCase()) {
            bool = true;
          }
        });
      }

      if (bool) {
        return;
      } else {
        MessageService.addGenreToData(newName);
        const g = {
          genreName: newName,
          genreId: "",
        };
        this.genres.push(g);
        this.newGenre = "";
      }
    },
  },
};
</script>

<style>
</style>