<template>
  <div class="card">
    <div id="profile-and-genres">
      <div class="profile">
        <img id="profile-picture" v-bind:src="band.profilePic" />
        <div id="everything-but-the-profile-picture">
          <div id="name-and-buttons">
            <h1 id="band-name">{{ band.name }}</h1>
            <button
              class="follow-button"
              @click="unfollowBand"
              v-if="status === true"
            >
              Following
            </button>
            <button class="follow-button" @click="followBand" v-else>
              Follow
            </button>
            <router-link
              :to="{
                name: 'bands-edit-profile',
                params: { id: this.$route.params.id },
              }"
            >
            </router-link>
          </div>

          <div>
            <p id="description">{{ band.description }}</p>
            <p id="members">{{ band.members }}</p>
          </div>
        </div>
      </div>
      <div class="genre-box">
        <ul v-for="genre in genres" v-bind:key="genre.id">
          <li>{{ genre.genreName }}</li>
        </ul>
      </div>
    </div>
    <button v-if="band.userID == this.$store.state.user.id">
      Edit Profile
    </button>
    <div id="create-note">
      <button
        id="create-note-button"
        v-if="band.userID == this.$store.state.user.id"
        @click="isReadyForMessage = !isReadyForMessage"
      >
        Write a Note
      </button>
      <textarea
        type="text"
        placeholder="Type Message Here"
        v-show="isReadyForMessage"
        v-model="messageText"
      />
      <button
        id="send-note-button"
        v-show="isReadyForMessage"
        @click="sendMessage"
      >
        Send
      </button>
    </div>
    <h2>Upcoming Shows</h2>
    <div class="upcoming-shows">
      <ul v-for="show in shows" v-bind:key="show.id">
        <li>
          {{ show.showName }} <br />
          {{ show.showLocation }} <br />
          {{ show.showDate }}
        </li>
      </ul>
    </div>
    <div class="picture-gallery">
      <h2>Gallery</h2>
      <ul v-for="picture in pictures" v-bind:key="picture.id">
        <li>
          <img v-bind:src="picture.pic_url" />
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import MessageService from "../services/MessageService";

export default {
  name: "page-detail",
  data() {
    return {
      band: {},
      pictures: [],
      shows: [],
      genres: [],
      status: "",
      follow: "Follow",
      isReadyForMessage: false,
      messageText: "",
      showEdits: false,
    };
  },
  created() {
    const bandId = parseInt(this.$route.params.id);
    MessageService.getBandPage(bandId).then((r) => {
      this.band = r.data;
    });
    MessageService.getPictures(bandId).then((r) => {
      this.pictures = r.data;
    });
    MessageService.getShows(bandId).then((r) => {
      this.shows = r.data;
    });
    MessageService.getGenres(bandId).then((r) => {
      this.genres = r.data;
    });
    MessageService.checkFollowStatus(bandId).then((r) => {
      this.status = r.data;
    });
    if (this.band.userId == this.$store.state.user.id) {
      this.showEdits = true;
    }
  },
  methods: {
    followBand() {
      const bandId = parseInt(this.$route.params.id);
      MessageService.follow(bandId).then(() => {});
      this.status = true;
    },
    unfollowBand() {
      const bandId = parseInt(this.$route.params.id);
      MessageService.unfollow(bandId).then(() => {});
      this.status = false;
    },
    sendMessage() {
      const bandId = parseInt(this.$route.params.id);
      const mes = this.messageText;
      MessageService.sendMessage(bandId, mes).then(() => {});
      this.messageText = "";
      this.isReadyForMessage = false;
    },
  },
  computed: {
    getRouteId() {
      return this.$route.params.id;
    },
  },
};
</script>

<style scoped>
.card {
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
#profile-and-genres {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.profile {
  display: flex;
}
#profile-picture {
  align-self: flex-start;
  width: 10vw;
  height: 20vh;
  border-radius: 50%;
  object-fit: cover;
  object-position: center;
}
#everything-but-the-profile-picture {
  display: flex;
  flex-direction: column;
}
#band-name {
  font-size: 3em;
  margin-left: 2%;
}
.follow-button {
  border-radius: 50px;
  border-color: #ef8354;
  color: #fff;
  text-transform: uppercase;
  font-size: 1em;
  letter-spacing: 2px;
  background-color: #ef8354;
  margin-left: 5%;
  width: 7.5vw;
  height: 5vh;
}
.button-style {
  border-radius: 10rem;
  border-color: #ef8354;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: 10%;
  background-color: #ef8354;
  margin-left: 2%;
  width: 10vw;
}
#name-and-buttons {
  width: 30vw;
  display: flex;
  flex-direction: row;
  align-items: center;
}
#description {
  font-style: italic;
  margin-left: 2%;
  font-size: 2em;
}
#members {
  margin-left: 2%;
  font-size: 2em;
}
.genre-box {
  display: flex;
  justify-self: flex-start;
  margin-top: 4%;
}
.genre-box li {
  list-style: none;
  padding-right: 2vw;
  padding-left: 2vw;
  padding-top: 1vh;
  padding-bottom: 1vh;
  background-color: #ef8354;
  border-radius: 10px;
  font-weight: bold;
  color: white;
}
.button-style:hover {
  background-color: #ee7642;
}
#create-note {
  display: flex;
  width: 75vw;
  align-self: flex-start;
  margin-left: 5%;
  margin-top: 2%;
}
#create-note-button {
  padding: 0.75rem 1.25rem;
  border-radius: 10rem;
  background-color: #ef8354;
  color: #fff;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: 0.15rem;
  border-color: #ef8354;
}
#create-note-button:hover {
  background-color: #ee7642;
}
#create-note textarea {
  width: 50vw;
  font-size: 2em;
  border-radius: 20px 0px 0px 20px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
#send-note-button {
  background-color: #2d3142;
  border-radius: 0px 2rem 2rem 0px;
  color: #fff;
  font-size: 2em;
  padding: 2%;
}
.upcoming-shows {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  width: 75vw;
  margin-left: 5%;
  background-color: #2d3142;
  padding-top: 2%;
  margin-bottom: 3%;
  margin-top: 2%;
}
.upcoming-shows h2 {
  margin-left: 5%;
  margin-bottom: 3%;
  font-size: 1.7em;
  color: white;
  writing-mode: vertical-rl;
  text-orientation: upright;
}
.upcoming-shows ul li {
  list-style: none;
  font-size: 1.5em;
  color: white;
  background: #ef8354;
  padding-top: 10%;
  border-radius: 100%;
  text-align: center;
  height: 15vh;
  width: 15vw;
  margin-right: 10%;
}
.picture-gallery h2 {
  margin-left: 5%;
  margin-bottom: 3%;
  font-size: 1.7em;
  color: white;
  writing-mode: vertical-rl;
  text-orientation: upright;
}
#shows {
  margin-left: 2%;
}
.picture-gallery {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  width: 75vw;
  margin-left: 5%;
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
</style>