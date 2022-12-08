<template>
  <div>
    <h1>Create a Band</h1>
    <input
      class="type"
      name="name"
      type="text"
      placeholder="Band Name"
      v-model="newBand.name"
    />

    <input
      type="text"
      class="type"
      placeholder="Description"
      v-model="newBand.description"
    />
    <input
      class="type"
      type="text"
      placeholder="Members"
      v-model="newBand.members"
    />
    <input
      class="type"
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
h1 {
  justify-content: center;
}

.type {
  width: 50%;
}
</style>