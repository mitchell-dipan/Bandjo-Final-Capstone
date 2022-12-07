<template>
  <div>
    <h1>{{ band.name }}</h1>
    <h2>{{ band.members }}</h2>
    <p>{{ band.description }}</p>
  </div>
</template>

<script>
import MessageService from "../services/MessageService";

export default {
  name: "page-detail",
  data() {
    return {
      band: {},
    };
  },
  created() {
    const bandId = parseInt(this.$route.params.id);
    MessageService.getBandPage(bandId).then((r) => {
      this.band = r.data;
    });
  },
};
</script>

<style>
</style>