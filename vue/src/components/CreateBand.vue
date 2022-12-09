<template>
  <div class="main">
    <div>
      <h1 id="title">Create a Band</h1>
      <input
        class="type"
        name="name"
        type="text"
        placeholder="Band Name"
        v-model="band.name"
      />
      <input
        type="text"
        class="type"
        placeholder="Description"
        v-model="band.description"
      />
      <input
        class="type"
        type="text"
        placeholder="Members"
        v-model="band.members"
      />
      <input
        class="type"
        type="text"
        placeholder="Profile pic url"
        v-model="band.profilePic"
      />
      <div id="search">
        <input
          class="type"
          type="text"
          id="genreNameFilter"
          v-model="searchGenre.genreName"
          placeholder="Type any genre..."
        />
        <div id="results-box">
          <div
            v-for="genre in filteredGenres"
            v-bind:key="genre.genreId"
            class="results"
          >
            <p @click="addGenre(genre.genreName)">{{ genre.genreName }}</p>
          </div>
        </div>
      </div>
      <div>
        <h1>Add Show's</h1>
        <input
          class="type"
          type="text"
          placeholder="Name of Show"
          v-model="show.showName"
        />
        <input
          class="type"
          type="date"
          placeholder="Show date"
          v-model="show.showDate"
        />
        <input
          class="type"
          type="text"
          placeholder="Show Location"
          v-model="show.showLocation"
        />
        <button class="button" @click="addShow()">Add Show</button>
      </div>
      <div>
        <h1>Add Pictures to Your Gallery</h1>
        <input
          class="type"
          type="text"
          placeholder="Band Pictures"
          v-model="picture.pic_url"
        />
        <button class="button" @click="addPicture()">Add Picture</button>
      </div>
    </div>
    <button class="button" @click.prevent="submitForm()">create</button>
  </div>
</template>
<script>
import MessageService from "../services/MessageService";
export default {
  data() {
    return {
      band: {
        name: "",
        description: "",
        members: "",
        userID: "",
        profilePic: "",
      },
      picture: {
        band_id: "",
        pic_url: "",
      },
      show: {
        bandId: "",
        showName: "",
        showDate: "",
        showLocation: "",
      },
      genre: {
        genreId: "",
        genreName: "",
      },
      searchGenre: {
        genreId: "",
        genreName: "",
      },
      pictures: [],
      shows: [],
      genresFromData: [],
      genres: [],
    };
  },
  created() {
    MessageService.getAllGenres().then((response) => {
      this.genresFromData = response.data;
    });
  },
  methods: {
    submitForm() {
      const newBand = {
        name: this.band.name,
        description: this.band.description,
        members: this.band.members,
        user_id: this.$store.state.user.id,
        profilePic: this.band.profilePic,
      };
      MessageService.createBandPage(newBand).then((response) => {
        let newId = response.data;
        this.pictures.forEach((picture) => {
          MessageService.addPicture(newId, picture).then(() => {});
        });
        this.shows.forEach((show) => {
          MessageService.addShow(newId, show).then(() => {});
        });
        this.genres.forEach((g) => {
          MessageService.addGenreToBand(newId, g).then(() => {});
        });
        this.$router.push(`/bands/${newId}`);
      });
    },
    addPicture() {
      const newPic = {
        pic_url: this.picture.pic_url,
      };
      this.pictures.push(newPic);
      this.picture.pic_url = "";
    },
    addShow() {
      const newShow = {
        showName: this.show.showName,
        showDate: this.show.showDate,
        showLocation: this.show.showLocation,
      };
      this.shows.push(newShow);
      this.show.showName = "";
      this.show.showDate = "";
      this.show.showLocation = "";
    },
    addGenre(g) {
      const newGenre = {
        genreName: g,
        // genreId: this.searchGenre.genreId,
      };
      this.genres.push(newGenre);
      this.searchGenre.genreName = "";
      // this.searchGenre.genreId = "";
    },
  },
  computed: {
    currentUserId() {
      return this.$store.state.user.id;
    },
    filteredGenres() {
      let genreList = this.genresFromData;
      if (this.searchGenre.genreName != "") {
        genreList = genreList.filter((genre) =>
          genre.genreName
            .toLowerCase()
            .includes(this.searchGenre.genreName.toLowerCase())
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
  margin-left: 10%;
  width: 70%;
  border-left: #2d3142 solid 2px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.type {
  width: 80%;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 16px;
  text-align: center;
  padding: 5%;
  margin-top: 3%;
  background-color: white;
}
.type::placeholder {
  font-size: 2em;
  text-align: center;
}
#results-box {
  display: flex;
  align-items: center;
}
.results p {
  margin-top: 5%;
  margin-left: 2%;
  padding-right: 1vw;
  padding-left: 1vw;
  padding-top: 1vh;
  padding-bottom: 1vh;
  background-color: #ef8354;
  border-radius: 10px;
  font-weight: bold;
  color: white;
}
h1 {
  font-weight: lighter;
  font-size: 3em;
}
.button {
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
}
</style>