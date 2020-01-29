<template>
    <v-card class="mx-auto cyan lighten-4" raised>
        <v-card-title>
             ประวัตินักศึกษา
            <v-spacer></v-spacer>
            <v-text-field
                    single-line
                    hide-details
                    v-model="search"
                    label="ค้นหาราชื้อนักศึกษา"
                    append-icon="mdi-account-search"
            ></v-text-field>
        </v-card-title>
        <v-data-table
                :headers="headers"
                no-results-text="ไม่พบข้อมูล"
                no-data-text="ไม่มีข้อมูลตาราง"
                :search="search"
                :items="teachTable"
        ></v-data-table>
    </v-card>
</template>


<script>
    export default {
        data: () => ({
            headers: [
                 {text: "รหัสนักศึกษา",value: "student_id"},
                 { text: "คำนำหน้า", value: "prefix.name" },
                 { text: "ชื่อ", value: "student_name" },
                 { text: "สาขาวิชา", value: "major.name" },
                 { text: "ชั้นปีการศึกษา", value: "year.year_name" },
                 { text: "Email", value: "student_email" },
                 { text: "เบอร์โทรศัพท์", value: "student_phone" }
            ],
            search: null,
            teachTable: []
        }),
        methods: {
            async getStudent() {
                await this.$http.get('student').then(response => {
                    this.teachTable = response.data
                })
            }
        },
        async created() {
            await this.getStudent()
        },
    };
</script>