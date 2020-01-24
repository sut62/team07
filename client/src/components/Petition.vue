<template>
    <v-container>
        <v-form ref="form" v-model="valid" persistent max-width="500px">
            <v-layout text-center wrap>
                <v-container>
                    <div>
                        <p class="font-weight-black display-1">ยื่นคำร้อง</p>
                    </div>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="12" sm="4">
                            <v-select
                                    label="รหัสนักศึกษา"
                                    outlined
                                    v-model="petition.studentId"
                                    :items="student"
                                    item-text="student_id"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาเลือกรหัสนักศึกษา']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col class="d-flex" cols="12" sm="4">
                            <v-select
                                    label="ประเภทคำร้อง"
                                    outlined
                                    v-model="petition.petitionTypeId"
                                    :items="petitionType"
                                    item-text="name"
                                    item-value="id"
                                    :rules="[(v) => !!v || 'กรุณาเลือกประเภทคำร้อง']"
                                    required
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="12" sm="4">
                            <v-textarea
                                    v-model="petition.detail"
                                    label="รายละเอียด"
                                    auto-grow
                                    outlined
                                    rows="1"
                                    row-height="15"
                                    clearable
                                    :rules="[(v) => !!v || 'กรุณาใส่รายละเอียด 5-200 ตัวอักษร']"
                                    required
                            ></v-textarea>
                        </v-col>
                    </v-row>
                    <v-row justify="center">
                        <v-col cols="12">
                            <v-btn :disabled="!valid" color="success" @click="savePetition">Save</v-btn>
                            <v-snackbar v-model="snackbar">{{text}}</v-snackbar>
                            <v-btn style="margin-left: 15px;" @click="clear">Reset</v-btn>
                        </v-col>
                    </v-row>
                </v-container>
            </v-layout>
        </v-form>
    </v-container>
</template>


<script>
/* eslint-disable */
export default {
  name: "petition",
  data() {
    return {
        petition: {
        studentId: [],
        petitionTypeId: []
      },
      text: null,
      valid: false,
      snackbar: false,
      student: null,
      petitionType: null,
      detail: null
    };
  },

  methods: {
  /* eslint-disable no-console */

    getStudent() {
      this.$http
        .get("/student")
        .then(response => {
          this.student = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getPetitionType() {
      this.$http
        .get("/petitionType")
        .then(response => {
          this.petitionType = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    savePetition() {
        this.$http
            .post(
                "/petition/" +
                this.petition.studentId +
                "/" +
                this.petition.petitionTypeId +
                "/" +
                this.petition.detail,
                this.petition
            )
            .then(response => {
                console.log(response);
                this.text = 'บันทึกข้อมูลสำเร็จ';
                this.snackbar = true;
                this.$refs.form.reset();
            })
            .catch(e => {
                console.log(e);
                this.text = 'บันทึกข้อมูลไม่สำเร็จ';
                this.snackbar = true;
            });
    },

    clear() {
      this.$refs.form.reset();
    },

    /* eslint-enable no-console */
  },

  mounted() {
      this.getStudent();
      this.getPetitionType();
  }
};
</script>
