<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="校区" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="campusName">
              <j-dict-select-tag type="list" v-model="model.campusName" dictCode="school_campus,campus_name,id" placeholder="请选择校区" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="年级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="grade">
              <j-dict-select-tag type="list" v-model="model.grade" dictCode="grade" placeholder="请选择年级" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="班级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="className">
              <j-dict-select-tag type="list" v-model="model.className" dictCode="class_inf,class_name,id" placeholder="请选择班级" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="学号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stdId">
              <a-input v-model="model.stdId" placeholder="请输入学号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="姓名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stdName">
              <a-input v-model="model.stdName" placeholder="请输入姓名"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sex">
              <j-dict-select-tag type="list" v-model="model.sex" dictCode="sex" placeholder="请选择性别" />
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
    name: 'StdInfForm',
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
           stdId: [
              { required: false},
              { validator: (rule, value, callback) => validateDuplicateValue('std_inf', 'std_id', value, this.model.id, callback)},
           ],
        },
        url: {
          add: "/student/stdInf/add",
          edit: "/student/stdInf/edit",
          queryById: "/student/stdInf/queryById"
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