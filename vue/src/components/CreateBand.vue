<template>
  <div>
    <h1>Create a Band</h1>
    <input type="text" placeholder="Band Name" v-model="newBand.name" />
    <input
      type="text"
      placeholder="Description"
      v-model="newBand.description"
    />
    <input type="text" placeholder="Members" v-model="newBand.members" />
    <input
      type="text"
      placeholder="Profile pic url"
      v-model="newBand.profilePic"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      newBand: {
        name: "",
        description: "",
        members: "",
        profilePic: "",
        userID: "",
      },
      user: {},
    };
  },
};
</script>

<style>
</style>