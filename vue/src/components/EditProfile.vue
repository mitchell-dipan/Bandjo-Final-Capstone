<template>
  <div>
    <input type="text" v-model="updateBand.name" />
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
    };
  },
  created() {
    const bandId = parseInt(this.$route.params.id);
    MessageService.getBandPage(bandId).then((r) => {
      this.band = r.data;
      this.updateBand.name = this.band.name;
    });
    // this.updateBand.description = this.band.description;
    // this.updateBand.members = this.band.members;
    // this.updateBand.user_id = this.band.user_id;
    // this.updateBand.profilePic = this.band.profilePic;
  },
};
</script>

<style scoped>
</style>