<template>
  <div>
    <h1>Create a Band</h1>
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
        userID: 4,
        profilePic: "",
      },
    };
  },
  methods: {
    submitForm() {
      const newBand = {
        name: this.band.name,
        description: this.band.description,
        members: this.band.members,
        user_id: 4,
        profilePic: this.band.profilePic,
      };

      MessageService.createBandPage(newBand).then((response) => {
        let newId = response.data;
        this.$router.push(`/bands/${newId}`);
      });
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
h1 {
  justify-content: center;
}

.type {
  width: 50%;
}
</style>