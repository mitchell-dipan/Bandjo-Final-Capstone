<template>
  <div class="main">
    <h1 id="title">Create a Band</h1>
    <div class="band-info">
      <input
        name="name"
        type="text"
        placeholder="Band Name"
        v-model="band.name"
      />
    </div>
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
    <div class="genre-search">
      <input
        type="text"
        id="genreNameFilter"
        v-model="searchGenre.genreName"
        placeholder="Type any genre..."
      />
      <div v-for="genre in filteredGenres" v-bind:key="genre.genreId">
        <p @click="addGenre(genre.genreName)">{{ genre.genreName }}</p>
      </div>
    </div>
    <div class="add-show-box">
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
      <button @click="addShow()">Add Show</button>
    </div>
    <div class=".add-pictures">
      <input
        class="type"
        type="text"
        placeholder="Band Pictures"
        v-model="picture.pic_url"
      />
      <button @click="addPicture()">Add Picture</button>
    </div>
    <button @click.prevent="submitForm()">create</button>
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
  align-items: center;
  flex-direction: column;
  height: 90vh;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
h1 {
  font-weight: lighter;
  font-size: 4em;
  margin-bottom: 2%;
}
.main input {
  width: 50%;
  padding: 2%;
  font-size: 2em;
  border-radius: 20px;
  border: 1px white solid;
  margin-bottom: 2%;
}
.genre-search {
  display: flex;
  flex-direction: column;
  align-self: flex-end;
  width: 50vw;
}
.genre-search p {
  padding-right: 2vw;
  padding-left: 2vw;
  padding-top: 1vh;
  padding-bottom: 1vh;
  background-color: #ef8354;
  border-radius: 10px;
  font-weight: bold;
  color: white;
}
</style>