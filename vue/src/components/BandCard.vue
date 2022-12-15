<template>
  <div class="card">
    <div id="background-color">
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
                <button
                  id="edit-profile"
                  v-if="band.userID == this.$store.state.user.id"
                >
                  Edit Profile
                </button>
              </router-link>
            </div>

            <div id="description-and-members">
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
    </div>
    <div id="create-note" v-if="band.userID == this.$store.state.user.id">
      <button
        id="create-note-button"
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
    <h2 class="headline">Upcoming Shows</h2>
    <div class="upcoming-shows">
      <ul v-for="show in shows" v-bind:key="show.id">
        <li>
          <div id="show-date">
            {{ show.showDate }}
          </div>
          <div id="show-name">{{ show.showName }} <br /></div>
          <div id="show-location">{{ show.showLocation }} <br /></div>
        </li>
      </ul>
      <div id="vl"></div>
    </div>
    <h2 class="headline">Gallery</h2>
    <div class="picture-gallery">
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
  align-items: center;
  padding-top: 2%;
  padding-bottom: 3%;
  margin-left: 6.25%;
  width: 79vw;
}
#edit-profile {
  border-radius: 50px;
  border-color: #2d3142;
  border: none;
  color: #fff;
  text-transform: uppercase;
  font-size: 1em;
  letter-spacing: 2px;
  background-color: white;
  color: #2d3142;
  margin-left: 5%;
  margin-top: 1%;
  width: 7.5vw;
  height: 5vh;
}
#edit-profile:hover {
  background-color: rgb(221, 221, 221);
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
  margin-left: 0%;
}
.follow-button {
  border-radius: 50px;
  border-color: #2d3142;
  border: none;
  color: #fff;
  text-transform: uppercase;
  font-size: 1em;
  letter-spacing: 2px;
  background-color: white;
  color: #2d3142;
  margin-left: 5%;
  margin-top: 1%;
  width: 7.5vw;
  height: 5vh;
}
.follow-button:hover {
  background-color: rgb(221, 221, 221);
}
#name-and-buttons {
  width: 120%;
  display: flex;
  margin-left: 2%;
  flex-direction: row;
  align-items: center;
}
#description-and-members {
  display: flex;
  flex-direction: column;

  justify-content: center;
}
#description {
  font-style: italic;
  margin-left: 3%;
  font-size: 2em;
}
#members {
  margin-left: 3%;
  font-size: 1.7em;
}
.genre-box {
  align-self: flex-start;
  display: flex;
  align-self: center;
  padding-top: 4%;
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
  border: none;
  background-color: #fff;
  color: #2d3142;
  text-transform: uppercase;
  font-size: 1rem;
  letter-spacing: 0.15rem;
  border-color: #ef8354;
}
#create-note-button:hover {
  background-color: rgb(221, 221, 221);
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
.headline {
  align-self: flex-start;
  margin-left: 5%;
  margin-top: 4%;
  margin-bottom: 2%;
  font-size: 1.8em;
  font-weight: 500;
  letter-spacing: 0.05em;
}
.upcoming-shows {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  width: 75vw;
  margin-left: 2%;
  padding-top: 2%;
  border-radius: 20px;
}
.upcoming-shows ul li {
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
.picture-gallery {
  display: flex;
  align-self: flex-start;
  flex-wrap: wrap;
  width: 75vw;
  margin-left: 2%;
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