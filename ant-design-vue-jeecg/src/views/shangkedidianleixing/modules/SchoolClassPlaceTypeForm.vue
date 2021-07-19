<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24" >
            <a-form-model-item label="校区" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="campusName">
              <j-dict-select-tag type="list" v-model="model.campusName" dictCode="school_campus,campus_name,id" placeholder="请选择校区" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="类型编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="typeNum">
              <a-input v-model="model.typeNum" placeholder="请输入类型编号" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="上课地点类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="typeName">
              <a-input v-model="model.typeName" placeholder="请输入上课地点类型" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="维护人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="typeAdmin">
              <a-input v-model="model.typeAdmin" placeholder="请输入维护人" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="维护时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="editTime">
              <j-date placeholder="请选择维护时间" v-model="model.editTime" style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="上课地点管理" :key="refKeys[0]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[0]"
          :loading="schoolPlaceCtrlTable.loading"
          :columns="schoolPlaceCtrlTable.columns"
          :dataSource="schoolPlaceCtrlTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

  import { getAction } from '@/api/manage'
  import { FormTypes,getRefPromise,VALIDATE_NO_PASSED } from '@/utils/JEditableTableUtil'
  import { JEditableTableModelMixin } from '@/mixins/JEditableTableModelMixin'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'SchoolClassPlaceTypeForm',
    mixins: [JEditableTableModelMixin],
    components: {
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        model:{
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['schoolPlaceCtrl', ],
        tableKeys:['schoolPlaceCtrl', ],
        activeKey: 'schoolPlaceCtrl',
        // 上课地点管理
        schoolPlaceCtrlTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '编号',
              key: 'placeNum',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
            {
              title: '上课地点',
              key: 'placeName',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
            },
          ]
        },
        url: {
          add: "/school_class_place_type/schoolClassPlaceType/add",
          edit: "/school_class_place_type/schoolClassPlaceType/edit",
          queryById: "/school_class_place_type/schoolClassPlaceType/queryById",
          schoolPlaceCtrl: {
            list: '/school_class_place_type/schoolClassPlaceType/querySchoolPlaceCtrlByMainId'
          },
        }
      }
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
    },
    methods: {
      addBefore(){
        this.schoolPlaceCtrlTable.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.schoolPlaceCtrl.list, params, this.schoolPlaceCtrlTable)
        }
      },
      //校验所有一对一子表表单
      validateSubForm(allValues){
          return new Promise((resolve,reject)=>{
            Promise.all([
            ]).then(() => {
              resolve(allValues)
            }).catch(e => {
              if (e.error === VALIDATE_NO_PASSED) {
                // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                this.activeKey = e.index == null ? this.activeKey : this.refKeys[e.index]
              } else {
                console.error(e)
              }
            })
          })
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          schoolPlaceCtrlList: allValues.tablesValue[0].values,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },

    }
  }
</script>

<style scoped>
</style>