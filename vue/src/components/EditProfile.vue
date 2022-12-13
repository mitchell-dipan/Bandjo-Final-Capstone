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
      <div>
        <h2>Add Show's</h2>
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

        <h2>Upcoming Shows</h2>
        <div class="upcoming-shows">
          <ul v-for="show in shows" v-bind:key="show.id">
            <li>
              {{ show.showName }} <br />
              {{ show.showLocation }} <br />
              {{ show.showDate }}
              <button @click="deleteShow(show)">Delete</button>
            </li>
          </ul>
        </div>
      </div>
      <h2>Add Pictures to Your Gallery</h2>
      <input
        class="type"
        type="text"
        placeholder="Band Pictures"
        v-model="picture.pic_url"
      />
      <button class="button" @click="addPicture()">Add Picture</button>
      <div class="picture-gallery">
        <h2>Gallery</h2>
        <ul v-for="picture in pictures" v-bind:key="picture.id">
          <li>
            <img v-bind:src="picture.pic_url" />
            <button @click="removePicture(picture)">Delete</button>
          </li>
        </ul>
      </div>
      <button
        id="save-changes"
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
  margin-left: 25%;
  width: 70%;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#title {
  font-weight: lighter;
  font-size: 3em;
}
.main h2 {
  font-size: 3em;
  font-weight: 400;
  margin-top: 1%;
  margin-bottom: 1%;
}
.type {
  width: 50%;
  box-shadow: inset 2px 2px 5px #babecc, inset -5px -5px 10px white;
  border-radius: 16px;
  text-align: left;
  padding: 2%;
  margin-top: 1%;
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
  flex-wrap: wrap;
  background-color: #2d3142;
  width: 80%;
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
.picture-gallery h2 {
  margin-left: 5%;
  margin-bottom: 3%;
  font-size: 1.7em;
  color: white;
  writing-mode: vertical-rl;
  text-orientation: upright;
}

.picture-gallery {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  width: 70vw;
  margin-top: 2%;
  background-color: #2d3142;
  padding-top: 2%;
  margin-bottom: 5%;
}
.picture-gallery li {
  list-style: none;
}
.picture-gallery li img {
  width: 15vw;
  height: 30vh;
  object-fit: cover;
  opacity: 0.7;
  transition: 1s ease;
}
.picture-gallery li img:hover {
  opacity: 1;
  transition: 0.5s ease;
}
.upcoming-shows ul li {
  list-style: none;
  font-size: 1.5em;
  color: white;
  background: #ef8354;
  padding-top: 7%;
  padding-bottom: 7%;
  margin-bottom: 2%;
  text-align: center;
  width: 15vw;
  margin-right: 10%;
}
.button {
  padding: 0.75rem 1.25rem;
  border-radius: 5rem 5rem;
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
#add-show {
  display: flex;
  flex-direction: column;
}
.upcoming-shows {
  display: flex;
  flex-wrap: wrap;
}
</style>