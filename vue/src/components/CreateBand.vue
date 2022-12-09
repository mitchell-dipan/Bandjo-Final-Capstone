<template>
  <div>
    <h1 id="title">Create a Band</h1>
    <div>
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
      <div>
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
        <div id="but">
          <button @click="addShow()">Add Show</button>
        </div>
      </div>
      <div>
        <input
          class="type"
          type="text"
          placeholder="Band Pictures"
          v-model="picture.pic_url"
        />
        <div id="but">
          <button @click="addPicture()">Add Picture</button>
        </div>
      </div>
    </div>
    <div id="but">
      <button @click.prevent="submitForm()">create</button>
    </div>
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
      pictures: [],
      shows: [],
    };
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
  },
  computed: {
    currentUserId() {
      return this.$store.state.user.id;
    },
  },
};
</script>

<style>
.type {
  display: flex;
  width: 50%;
  margin-left: 35%;
}
#title {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: auto;
}
#but {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 30px 30px;
}
</style>