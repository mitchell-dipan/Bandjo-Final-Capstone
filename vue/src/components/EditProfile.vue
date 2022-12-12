<template>
  <div class="main">
    <h2>Name</h2>
    <input class="type" type="text" v-model="updateBand.name" />
    <h2>Description</h2>
    <input class="type" type="text" v-model="updateBand.description" />
    <h2>Members</h2>
    <input class="type" type="text" v-model="updateBand.members" />
    <h2>Profile Picture URL</h2>
    <input class="type" type="text" v-model="updateBand.profilePic" />
    <input
      type="text"
      id="genre-search-bar"
      v-model="searchGenre.genreName"
      placeholder="Type any genre..."
    />
    <div id="results-box">
      <div
        v-for="genre in filteredGenres"
        v-bind:key="genre.genreId"
        class="results"
      >
        <p class="genre-bubble" @click="addGenre(genre.genreName)">
          {{ genre.genreName }}
        </p>
      </div>
    </div>
    <div id="current-genres">
      <h3>Current Genres</h3>
      <div id="genre-list">
        <ul v-for="genre in yourGenres" v-bind:key="genre.id">
          <li class="genre-bubble">
            {{ genre.genreName }}
            <button @click="removeGenre(genre)">X</button>
          </li>
        </ul>
      </div>
    </div>

    <button
      id="save-changes"
      type="submit"
      @click="updateBandProfile(updateBand)"
    >
      Save Changes
    </button>
  </div>
</template>

<script>
import MessageService from "../services/MessageService";
export default {
  name: "edit-profile",
  data() {
    return {
      band: {},
      updateBand: {
        name: "",
        description: "",
        members: "",
        user_id: "",
        profilePic: "",
      },
      genre: {
        genreId: "",
        genreName: "",
      },
      searchGenre: {
        genreId: "",
        genreName: "",
      },
      genresFromData: [],
      yourGenres: [],
    };
  },
  created() {
    const bandId = parseInt(this.$route.params.id);
    MessageService.getBandPage(bandId).then((r) => {
      this.band = r.data;
      this.updateBand.name = this.band.name;
      this.updateBand.description = this.band.description;
      this.updateBand.members = this.band.members;
      this.updateBand.profilePic = this.band.profilePic;
    });
    MessageService.getGenres(bandId).then((r) => {
      this.yourGenres = r.data;
    });
    MessageService.getAllGenres().then((r) => {
      this.genresFromData = r.data;
    });
  },
  methods: {
    addGenre(g) {
      const newGenre = {
        genreName: g,
        // genreId: this.searchGenre.genreId,
      };
      MessageService.addGenreToBand(this.$route.params.id, newGenre);
      this.yourGenres.push(newGenre);
      this.searchGenre.genreName = "";
      // this.searchGenre.genreId = "";
    },
    removeGenre(genre) {
      const id = this.$route.params.id;
      MessageService.deleteGenreFromBand(id, genre.genreId).then(() => {});
      const index = this.yourGenres.indexOf(genre);
      this.yourGenres.splice(index, 1);
    },
    updateBandProfile(band) {
      MessageService.updateBand(this.$route.params.id, band);
      this.$router.push(`/bands/${this.$route.params.id}`);
    },
  },
  computed: {
    filteredGenres() {
      let genreList = this.genresFromData;
      if (this.searchGenre.genreName != "") {
        genreList = genreList.filter((genre) =>
          genre.genreName
            .toLowerCase()
            .startsWith(this.searchGenre.genreName.toLowerCase())
        );
        return genreList;
      } else {
        return [];
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
  align-items: center;
  margin-left: 20%;
  width: 70%;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.main h2 {
  align-self: flex-start;
  margin-top: 2%;
  margin-left: 5%;
  font-size: 2em;
  font-weight: 400;
}
.type {
  width: 80%;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 16px;
  text-align: left;
  padding: 5%;
  margin-top: 3%;
  background-color: white;
  font-size: 2em;
}
.type::placeholder {
  font-size: 2em;
  text-align: center;
}

#results-box {
  display: flex;
  align-items: center;
  align-self: flex-start;
  margin-left: 5%;
  margin-top: 1%;
}
.genre-bubble {
  margin-top: 5%;
  margin-left: 2%;
  padding-right: 2vw;
  padding-left: 2vw;
  padding-top: 2vh;
  padding-bottom: 2vh;
  background-color: #ef8354;
  border-radius: 10px;
  font-weight: bold;
  color: white;
}
#genre-search-bar {
  width: 80%;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 16px;
  text-align: left;
  padding: 5%;
  margin-top: 3%;
  background-color: white;
  font-size: 2em;
}
#genre-search-bar::placeholder {
  font-size: 2em;
  text-align: left;
}
#current-genres {
  display: flex;
  color: white;
  flex-direction: column;
  background-color: #2d3142;
  width: 30vw;
  padding: 5%;
  align-items: center;
  border-radius: 2rem;
  margin-top: 2%;
}
#current-genres h3 {
  align-self: flex-start;
}
#genre-list {
  display: flex;
  margin-top: 2%;
  align-self: flex-start;
}
#current-genres li {
  list-style: none;
}
#save-changes {
  padding: 0.75rem 1.25rem;
  border-radius: 10rem;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: 0.15rem;
  transition: all 0.3s;
  overflow: hidden;
  z-index: 1;
  background-color: #ef8354;
  margin-top: 2%;
  margin-bottom: 3%;
}
</style>