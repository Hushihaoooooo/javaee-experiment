<template>
  <el-card style="max-width: 480px">
    <template #header>个人信息</template>
    <el-form :model="userInfo" label-width="auto" style="max-width: 600px">
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="userInfo.userName" disabled />
      </el-form-item>
      <el-form-item label="姓名" prop="nickName">
        <el-input v-model="userInfo.nickName" placeholder="还未完善" />
      </el-form-item>
      <el-form-item label="姓别" prop="gender">
        <el-radio-group v-model="userInfo.gender" class="ml-4">
          <el-radio :value="0">男</el-radio>
          <el-radio :value="1">女</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="省份">
        <el-select v-model="userInfo.province" placeholder="请选择省份" @change="handleProvinceChange">
          <el-option
            v-for="province in provinces"
            :key="province"
            :label="province"
            :value="province"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="城市">
        <el-select v-model="userInfo.city" placeholder="请选择城市">
          <el-option
            v-for="city in cities"
            :key="city"
            :label="city"
            :value="city"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="userInfo.email" placeholder="还未完善" />
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="userInfo.mobile" placeholder="还未完善" />
      </el-form-item>
      <el-form-item label="QQ">
        <el-input v-model="userInfo.QQ" placeholder="还未完善" />
      </el-form-item>
      <el-form-item label="给大家的一句话">
        <el-input
          v-model="userInfo.intro"
          style="width: 240px"
          :rows="2"
          type="textarea"
          placeholder="还未完善"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateUserInfo">修改</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script lang="ts" setup>
import { ref, watch } from 'vue'
import { userInfoService, userInfoUpdateService } from '@/api/user'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useUserInfoStore } from '@/stores/userInfo'

const userInfoStore = useUserInfoStore()

// do not use same name with ref
const userInfo = ref({
  ...userInfoStore.info
})

const provinces = ref([
  '北京', '上海', '天津', '重庆', '广东', '江苏', '浙江', '山东', '福建', '海南', '四川', '湖北', '湖南', '河北', '河南', '山西', '陕西', '安徽', '江西', '广西', '云南', '贵州', '辽宁', '吉林', '黑龙江', '内蒙古', '宁夏', '青海', '西藏', '新疆', '甘肃'
])

interface CitiesData {
  [key: string]: string[]
}

const citiesData: CitiesData = {
  '北京': ['北京市'],
  '上海': ['上海市'],
  '天津': ['天津市'],
  '重庆': ['重庆市'],
  '广东': ['广州市', '深圳市', '珠海市', '汕头市', '佛山市', '东莞市'],
  '江苏': ['南京市', '苏州市', '无锡市', '常州市', '扬州市'],
  '浙江': ['杭州市', '宁波市', '温州市', '绍兴市', '金华市'],
  '山东': ['济南市', '青岛市', '烟台市', '潍坊市', '临沂市'],
  '福建': ['福州市', '厦门市', '泉州市', '漳州市', '莆田市'],
  '海南': ['海口市', '三亚市'],
  '四川': ['成都市', '绵阳市', '德阳市', '南充市', '泸州市'],
  '湖北': ['武汉市', '黄石市', '襄阳市', '宜昌市', '鄂州市'],
  '湖南': ['长沙市', '株洲市', '湘潭市', '衡阳市', '岳阳市'],
  '河北': ['石家庄市', '唐山市', '秦皇岛市', '邯郸市', '邢台市'],
  '河南': ['郑州市', '开封市', '洛阳市', '平顶山市', '安阳市'],
  '山西': ['太原市', '大同市', '阳泉市', '长治市', '晋城市'],
  '陕西': ['西安市', '铜川市', '宝鸡市', '咸阳市', '渭南市'],
  '安徽': ['合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市'],
  '江西': ['南昌市', '景德镇市', '萍乡市', '九江市', '新余市'],
  '广西': ['南宁市', '柳州市', '桂林市', '梧州市', '北海市'],
  '云南': ['昆明市', '曲靖市', '玉溪市', '保山市', '昭通市'],
  '贵州': ['贵阳市', '六盘水市', '遵义市', '安顺市', '毕节市'],
  '辽宁': ['沈阳市', '大连市', '鞍山市', '抚顺市', '本溪市'],
  '吉林': ['长春市', '吉林市', '四平市', '辽源市', '通化市'],
  '黑龙江': ['哈尔滨市', '齐齐哈尔市', '鸡西市', '鹤岗市', '双鸭山市'],
  '内蒙古': ['呼和浩特市', '包头市', '乌海市', '赤峰市', '通辽市'],
  '宁夏': ['银川市', '石嘴山市', '吴忠市', '固原市', '中卫市'],
  '青海': ['西宁市', '海东市'],
  '西藏': ['拉萨市', '日喀则市', '昌都市', '林芝市', '山南市'],
  '新疆': ['乌鲁木齐市', '克拉玛依市', '吐鲁番市', '哈密市', '阿克苏市'],
  '甘肃': ['兰州市', '嘉峪关市', '金昌市', '白银市', '天水市'],
}

const cities = ref<string[]>([])

watch(() => userInfo.value.province, (newProvince) => {
  cities.value = citiesData[newProvince] || []
  userInfo.value.city = ''
})

const handleProvinceChange = (value: string) => {
  userInfo.value.province = value
  userInfo.value.city = '' // 清空城市选择
}
const getUserInfo = async () => {
  let res = await  userInfoService();
  console.log(res.data.data);
  userInfoStore.setInfo(res.data.data)
}
getUserInfo()
const updateUserInfo = async () => {
  console.log("dudu")
  console.log(userInfo.value)
  ElMessageBox.confirm(
    '你确定要修改个人信息吗？',
    '温馨提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(async () => {
    let res = await userInfoUpdateService(userInfo.value)
    ElMessage.success(res.data.message ? res.data.message : '修改成功')
  }).catch(() => {
    ElMessage({
      type: 'info',
      message: '用户取消修改'
    })
  })
  await getUserInfo()
}
</script>