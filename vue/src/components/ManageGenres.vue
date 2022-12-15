<template>
  <div class="main">
    <h1>Manage Genres</h1>
    <h3>Select genres to delete</h3>
    <div id="all-genres">
      <div
        v-for="genre in genres"
        :key="genre.genreId"
        @click="selectGenre(genre)"
      >
        <p>{{ genre.genreName }}</p>
      </div>
    </div>
    <h2 v-if="chosenGenres.length > 0">Genres To Delete</h2>
    <h3 v-if="chosenGenres.length > 0">Select genres to undo</h3>
    <div id="selected-genres" v-if="chosenGenres.length > 0">
      <div
        v-for="genre in chosenGenres"
        :key="genre.genreId"
        @click="unselectGenre(genre)"
      >
        <p>{{ genre.genreName }}</p>
      </div>
    </div>
    <button class="button" v-if="chosenGenres.length > 0" @click="deleteGenres">
      Delete
    </button>
    <div>
      <h2>Add Genre</h2>
      <input
        type="text"
        v-model="newGenre"
        placeholder="Enter any genre here.."
      />
    </div>
    <button class="button" @click="addGenre()">Add</button>
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
      const index = this.genres.indexOf(genre);
      this.genres.splice(index, 1);
    },
    unselectGenre(genre) {
      const index = this.chosenGenres.indexOf(genre);
      this.chosenGenres.splice(index, 1);
      this.genres.push(genre);
    },
    deleteGenres() {
      this.chosenGenres.forEach((genre) => {
        MessageService.deleteGenres(genre.genreName).then(() => {
          const index = this.genres.indexOf(genre);
          this.genres.splice(index, 1);
        });
        this.chosenGenres = [];
      });
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
        MessageService.addGenreToData(newName).then(() => {
          const g = {
            genreName: newName,
            genreId: "",
          };
          this.genres.push(g);
        });

        this.newGenre = "";
      }
    },
  },
};
</script>

<style scoped>
.main {
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 30%;
  width: 100%;
  color: #fff;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.main h1 {
  align-self: flex-start;
  margin-top: 2%;
  margin-bottom: 2%;
  font-weight: lighter;
  font-size: 3em;
}
.main h3 {
  margin-bottom: 1%;
  font-style: italic;
  font-weight: 400;
}
.main h2 {
  align-self: flex-start;
  margin-top: 1%;
  margin-bottom: 0.7%;
  font-weight: lighter;
  font-size: 2em;
}
#all-genres {
  display: flex;
  justify-content: left;
  flex-wrap: wrap;
  width: 35vw;
  height: 100%;
  gap: 5px 50px;
}
#all-genres p {
  margin-top: 2%;
  text-align: center;
  width: 100%;
  padding-right: 1vw;
  padding-left: 1vw;
  padding-top: 1vh;
  padding-bottom: 1vh;
  background-color: #ef8354;
  border-radius: 10px;
  font-weight: bold;
  color: white;
}
#all-genres p:hover {
  background-color: #f17843;
}
#selected-genres {
  display: flex;
  justify-content: left;
  flex-wrap: wrap;
  width: 35vw;
  height: 100%;
  gap: 5px 50px;
}
#selected-genres p {
  margin-top: 2%;
  text-align: center;
  width: 100%;
  padding-right: 1vw;
  padding-left: 1vw;
  padding-top: 1vh;
  padding-bottom: 1vh;
  background-color: #fcfbf4;
  border-radius: 10px;
  font-weight: bold;
  color: #ef8354;
}
#selected-genres p:hover {
  background-color: #f0efec;
}
.button {
  border-radius: 50px;
  border-color: #2d3142;
  border: none;
  color: #fff;
  text-transform: uppercase;
  font-size: 1em;
  letter-spacing: 2px;
  background-color: white;
  color: #2d3142;
  margin-top: 1%;
  margin-bottom: 1%;
  width: 5vw;
  height: 5vh;
}
.button:hover {
  background-color: #2d3142;
  color: white;
}
input {
  width: 100%;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 16px;
  padding: 3%;
  margin-top: 1%;
  font-size: 1.5em;
  background-color: white;
}
</style>