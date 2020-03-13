<template>
    <v-container>
        <v-form ref="form" v-model="valid" persistent max-width="500px">
            <v-layout text-center wrap>
                <v-container>
                    <div>
                        <p class="font-weight-black display-1">ยื่นคำร้อง</p>
                    </div>
                    <v-row justify="center">
                        <v-col cols="12" sm="3">
                            <v-text-field
                                    v-model="petition.studentId"
                                    label="รหัสนักศึกษา"
                                    outlined
                                    clearable
                                    :rules="[(v) => !!v || 'กรุณาใส่รหัสนักศึกษา']"
                                    required
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="1">
                            <v-btn :color="success" @click="checkStudentId">ค้นหา</v-btn>
                            <v-snackbar v-model="snackbars">{{message}}</v-snackbar>
                        </v-Col>
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
        studentId: [localStorage.getItem("username")],
        petitionTypeId: []
      },
      text: null,
      message: null,
      valid: false,
      snackbar: false,
      snackbars: false,
      student: null,
      petitionType: null,
      detail: null
    };
  },

  methods: {
  /* eslint-disable no-console */

    checkStudentId() {
      this.$http
        .get("/student/" + this.petition.studentId)
        .then(response => {
          if (this.petition.studentId == response.data.student_id ) {
            this.message = 'พบรหัสนักศึกษา';
            this.snackbars = true;
          }
          this.petition.studentId = localStorage.getItem("username");
        })
        .catch(e => {
            console.log(e);
            this.message = 'ไม่พบรหัสนักศึกษา';
            this.snackbars = true;
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
                localStorage.getItem("username") +
                "/" +
                this.petition.petitionTypeId +
                "/" +
                this.petition.detail
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
      this.getPetitionType();
  }
};
</script>
