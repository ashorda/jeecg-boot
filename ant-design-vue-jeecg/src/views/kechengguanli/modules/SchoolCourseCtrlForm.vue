<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="校区" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="campus">
              <j-dict-select-tag type="list" v-model="model.campus" dictCode="school_campus,campus_name,id" placeholder="请选择校区" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="课程名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseName">
              <a-input v-model="model.courseName" placeholder="请输入课程名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="任课教师" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseTeacher">
              <a-input v-model="model.courseTeacher" placeholder="请输入任课教师"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="课程类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseType">
              <j-dict-select-tag type="list" v-model="model.courseType" dictCode="course_type" placeholder="请选择课程类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上课地点" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="coursePlace">
              <j-dict-select-tag type="list" v-model="model.coursePlace" dictCode="school_place_ctrl,place_name,place_name" placeholder="请选择上课地点" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="选课人数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="numOfCourse">
              <a-input v-model="model.numOfCourse" placeholder="请输入选课人数"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上课时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseTime">
              <a-input v-model="model.courseTime" placeholder="请输入上课时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="选课班级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="chosenClass">
              <j-multi-select-tag type="checkbox" v-model="model.chosenClass" dictCode="school_class_inf,grade,class_name,id" placeholder="请选择选课班级" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'SchoolCourseCtrlForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/school_course_ctrl/schoolCourseCtrl/add",
          edit: "/school_course_ctrl/schoolCourseCtrl/edit",
          queryById: "/school_course_ctrl/schoolCourseCtrl/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>