<template>
  <div class="main">
    <div>
      <h1 id="title">Edit Band</h1>
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
        class="type"
        v-model="searchGenre.genreName"
        placeholder="Type any genre..."
      />
      <h3 v-if="this.filteredGenres.length > 0" id="instructions">
        Select genres to add
      </h3>
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
      <h3 v-if="this.yourGenres.length > 0">Current Genres:</h3>
      <h3 v-if="this.yourGenres.length > 0" id="instructions">
        Select genres to undo
      </h3>
      <div id="current-genres">
        <div id="genre-list" v-for="genre in yourGenres" v-bind:key="genre.id">
          <p @click="removeGenre(genre)">
            {{ genre.genreName }}
          </p>
        </div>
      </div>
      <div id="add-shows">
        <h2>Add Shows</h2>
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
      </div>
      <button class="button" @click="addShow()">Add Show</button>

      <h2>Current Shows:</h2>
      <div id="current-shows">
        <ul v-for="show in shows" v-bind:key="show.id">
          <li>
            <div id="show-date">
              {{ show.showDate }}
            </div>
            <div id="show-name">{{ show.showName }} <br /></div>
            <div id="show-location">{{ show.showLocation }} <br /></div>
            <button @click="deleteShow(show)">Delete</button>
          </li>
        </ul>
        <div id="vl" v-if="this.shows.length > 0"></div>
      </div>
      <div id="add-pictures">
        <h2>Add Pictures to Your Gallery</h2>
        <input
          class="type"
          type="text"
          placeholder="Band Pictures"
          v-model="picture.pic_url"
        />
      </div>
      <button class="button" @click="addPicture()">Add Picture</button>
      <h3>Gallery:</h3>
      <div id="picture-gallery">
        <ul v-for="picture in pictures" v-bind:key="picture.id">
          <li>
            <img v-bind:src="picture.pic_url" />
            <button @click="removePicture(picture)">Delete</button>
          </li>
        </ul>
      </div>
      <button
        class="button"
        type="submit"
        @click="updateBandProfile(updateBand)"
      >
        Save Changes
      </button>
    </div>
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
      pictures: [],
      picture: { band_id: "", pic_url: "" },
      shows: [],
      show: {
        bandId: "",
        showName: "",
        showDate: "",
        showLocation: "",
      },
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
    MessageService.getPictures(bandId).then((r) => {
      this.pictures = r.data;
    });
    MessageService.getShows(bandId).then((r) => {
      this.shows = r.data;
    });
  },
  methods: {
    addGenre(g) {
      const newGenre = {
        genreName: g,
        // genreId: this.searchGenre.genreId,
      };
      let bool = true;
      this.yourGenres.forEach((g) => {
        if (g.genreName == newGenre.genreName) {
          bool = false;
        }
      });
      if (bool) {
        MessageService.addGenreToBand(this.$route.params.id, newGenre);
        this.yourGenres.push(newGenre);
        this.searchGenre.genreName = "";
      }
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
    removePicture(picture) {
      MessageService.deletePictureFromBandGallery(
        this.$route.params.id,
        picture.picture_id
      );
      const index = this.pictures.indexOf(picture);
      this.pictures.splice(index, 1);
    },
    addPicture() {
      const newPic = {
        pic_url: this.picture.pic_url,
      };
      this.pictures.push(newPic);
      this.picture.pic_url = "";
      MessageService.addPicture(this.$route.params.id, newPic);
    },
    deleteShow(show) {
      MessageService.deleteShow(this.$route.params.id, show.showId);
      const index = this.shows.indexOf(show);
      this.shows.splice(index, 1);
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
      MessageService.addShow(this.$route.params.id, newShow);
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
  color: white;
}
.main h1 {
  font-weight: lighter;
  font-size: 3em;
  margin-bottom: 2%;
}
.main h2 {
  font-size: 2.5em;
  font-weight: lighter;
  margin-top: 1%;
  margin-bottom: 2%;
}
.type {
  width: 70%;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 16px;
  text-align: left;
  padding: 3%;
  margin-bottom: 2%;
  font-size: 1.5em;
  background-color: white;
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
#current-genres {
  display: flex;
  align-items: center;
  margin-top: 3%;
  margin-bottom: 3%;
  gap: 5px 50px;
}
h3 {
  align-self: flex-start;
  font-size: 1.4em;
  font-weight: 500;
}
#instructions {
  font-size: 1em;
  font-weight: lighter;
  font-style: italic;
}
#current-genres p {
  margin-left: 8%;
  width: 100%;
  padding-right: 1vw;
  padding-left: 1vw;
  padding-top: 1vh;
  padding-bottom: 1vh;
  background-color: #ef8354;
  border-radius: 10px;
  font-weight: bold;
  text-align: center;
  color: white;
}
#current-genres button {
  color: black;
  background-color: #ef8354;
}
#current-shows {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  margin-left: 2%;
  margin-bottom: 2%;
  padding-top: 2%;
  border-radius: 20px;
}
#current-shows ul li {
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-size: 1.5em;
  list-style: none;
  color: white;
  text-align: left;
  border-left: 1px solid white;
  height: 20vh;
  width: 120%;
}
#current-shows button {
  color: black;
  background-color: #fff;
  border: none;
  border-radius: 15px;
  width: 50%;
  margin-top: 10%;
  margin-left: 2%;
}
#show-name {
  color: #ee7642;
  font-weight: 900;
  padding-left: 5%;
  margin-bottom: 2%;
}
#show-location {
  font-weight: 700;
  padding-left: 5%;
}
#show-date {
  font-size: 0.85em;
  padding-left: 5%;
}
#vl {
  height: 20vh;
  margin-left: 2%;
  border-left: 1px solid white;
  align-self: flex-end;
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
  margin-top: 2%;
  margin-bottom: 2%;
  width: 7.5vw;
  height: 5vh;
}
.button:hover {
  background-color: #2d3142;
  color: white;
}
#picture-gallery {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  margin-top: 2%;
  margin-bottom: 5%;
}
#picture-gallery li {
  list-style: none;
}
#picture-gallery li img {
  width: 15vw;
  height: 30vh;
  object-fit: cover;
  opacity: 0.7;
  transition: 1s ease;
}
#picture-gallery li img:hover {
  opacity: 1;
  transition: 0.5s ease;
}
#picture-gallery button {
  color: black;
  background-color: #fff;
  border: none;
  border-radius: 15px;
  width: 20%;
}

#add-show {
  display: flex;
  flex-direction: column;
}
</style>