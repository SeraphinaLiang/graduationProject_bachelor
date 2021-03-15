<template>
  <v-container>
    <v-btn
      style="margin: 10px"
      color="pink"
      outlined
      small
      @click="refresh"
    >
      <v-icon>mdi-cached</v-icon>
      refresh
    </v-btn>
    <v-row>
      <v-col>
        <h3>用户组成</h3>
        <div
          id="chartUser"
          style="width:90%;height:400px;"
        />
      </v-col>
      <v-col>
        <h3>用户增长</h3>
        <div
          id="chartUserIncrease"
          style="width:100%;height:400px;"
        />
      </v-col>
    </v-row>
    <v-row>
      <v-container>
        <h3>热门标签</h3>
        <div
          id="chartTag"
          style="width: 100%;height:400px;"
        />
      </v-container>
    </v-row>
    <v-row>
      <v-container>
        <h3>模块发帖统计</h3>
        <div
          id="chartModule"
          style="width: 100%;height:800px;"
        />
      </v-container>
    </v-row>
  </v-container>
</template>
<script>
  import * as echarts from 'echarts'

  export default {
    data () {
      return {
        userDis: [],
        tags: [],
      }
    },
    mounted () {
      this.refresh()
    },
    methods: {
      refresh () {
        this.userChart()
        this.tagChart()
        this.userIncreaseChart()
        this.moduleChart()
      },
      userChart () {
        const chart = echarts.init(document.getElementById('chartUser'))
        const _this = this
        this.$axios.get('/userDistribution').then(res => {
          _this.userDis = res.data.data

          const option = {
            backgroundColor: '#363636',
            visualMap: {
              show: false,
              min: -400,
              max: 300,
              inRange: {
                colorLightness: [0.3, 1],
              },
            },
            series: [
              {
                name: '用户组成',
                type: 'pie',
                radius: '55%',
                data: [
                  { value: _this.userDis[0], name: 'Female' },
                  { value: _this.userDis[1], name: 'Male' },
                  { value: _this.userDis[2], name: 'Other' },
                  { value: _this.userDis[3], name: 'NaN' },
                ],
                roseType: 'angle',
                label: {
                  normal: {
                    textStyle: {
                      color: 'rgba(255, 255, 255)',
                    },
                  },
                },
                labelLine: {
                  normal: {
                    lineStyle: {
                      color: 'rgba(255, 255, 255, 0.7)',
                    },
                  },
                },
                itemStyle: {
                  normal: {
                    color: '#ac54f8',
                    shadowBlur: 200,
                    shadowColor: 'rgb(95,97,97)',
                  },
                },
              },
            ],
          }
          chart.setOption(option)
        })
      },
      userIncreaseChart () {
        // Generate data
        const category = [] // 横坐标
        let dottedBase = +new Date('2021-2-15') // 开始日期
        const lineData = [] // line纵坐标 -- 累计用户数
        const barData = [] // bar纵坐标 -- 新增数

        const _this = this

        for (let i = 0; i < 30; i++) {
          const date = new Date(dottedBase += 3600 * 24 * 1000) // 增加一天
          category.push([
            date.getFullYear(),
            date.getMonth() + 1,
            date.getDate(),
          ].join('-'))
          const today = date.toLocaleDateString()

          _this.$axios.post('/userCountDate', today).then(res => {
            const num = res.data.data
            barData.push(num[0])
            lineData.push(num[1])
          })
        }
        const chart1 = echarts.init(document.getElementById('chartUserIncrease'))

        setTimeout(() => {
          const option = {
            backgroundColor: '#0f375f',
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'shadow',
              },
            },
            legend: {
              data: ['line', 'bar'],
              textStyle: {
                color: '#ccc',
              },
            },
            xAxis: {
              data: category,
              axisLine: {
                lineStyle: {
                  color: '#ccc',
                },
              },
            },
            yAxis: {
              splitLine: { show: false },
              axisLine: {
                lineStyle: {
                  color: '#ccc',
                },
              },
            },
            series: [{
              name: 'line',
              type: 'line',
              smooth: true,
              showAllSymbol: true,
              symbol: 'emptyCircle',
              symbolSize: 15,
              data: lineData,
            }, {
              name: 'bar',
              type: 'bar',
              barWidth: 10,
              itemStyle: {
                barBorderRadius: 5,
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    { offset: 0, color: '#14c8d4' },
                    { offset: 1, color: '#43eec6' },
                  ],
                ),
              },
              data: barData,
            }, {
              name: 'line',
              type: 'bar',
              barGap: '-100%',
              barWidth: 10,
              itemStyle: {
                color: new echarts.graphic.LinearGradient(
                  0, 0, 0, 1,
                  [
                    { offset: 0, color: 'rgba(20,200,212,0.5)' },
                    { offset: 0.2, color: 'rgba(20,200,212,0.2)' },
                    { offset: 1, color: 'rgba(20,200,212,0)' },
                  ],
                ),
              },
              z: -12,
              data: lineData,
            }, {
              name: 'dotted',
              type: 'pictorialBar',
              symbol: 'rect',
              itemStyle: {
                color: '#0f375f',
              },
              symbolRepeat: true,
              symbolSize: [12, 4],
              symbolMargin: 1,
              z: -10,
              data: lineData,
            }],
          }
          chart1.setOption(option)
        }, 1500)
      },
      tagChart () {
        const chart = echarts.init(document.getElementById('chartTag'))
        const _this = this
        this.$axios.get('/popularTag').then(res => {
          _this.tags = res.data.data
          const arr = []
          for (let i = 0; i < _this.tags.length; i++) {
            arr.push([_this.tags[i].name, _this.tags[i].num])
          }

          const option = {
            dataset: [{
              dimensions: ['name', 'hot'],
              source: arr,
            }, {
              transform: {
                type: 'sort',
                config: { dimension: 'hot', order: 'desc' },
              },
            }],
            xAxis: {
              type: 'category',
              axisLabel: { interval: 0, rotate: 30 },
            },
            yAxis: {},
            series: {
              type: 'bar',
              encode: { x: 'name', y: 'hot' },
              datasetIndex: 1,
            },
            color: [
              '#4bf6d4',
            ],
          }
          chart.setOption(option)
        })
      },
      moduleChart () {
        let allModule = [] // res-data
        const theSource = [['Timeline', 'January', 'February', 'March', 'April', 'May', 'June']]

        this.$axios.get('/getHotModule').then(res => {
          // console.log(res.data.data)
          allModule = res.data.data

          for (let k = 0; k < 5; k++) {
            let module = []
            module.push(allModule[k].name)
            module = module.concat(allModule[k].number)
            // module.push(allModule[k].number)
            theSource.push(module)
          }
          // console.log(theSource)
        })

        setTimeout(function () {
          const option = {
            legend: {},
            tooltip: {
              trigger: 'axis',
              showContent: false,
            },
            dataset: {
              source: theSource,
            },
            xAxis: { type: 'category' },
            yAxis: { gridIndex: 0 },
            grid: { top: '55%' },
            series: [
              { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
              { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
              { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
              { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
              { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
              {
                type: 'pie',
                id: 'pie',
                radius: '30%',
                center: ['50%', '25%'],
                emphasis: { focus: 'data' },
                label: {
                  formatter: '{b}: {@February} ({d}%)',
                },
                encode: {
                  itemName: 'Timeline',
                  value: 'February',
                  tooltip: 'February',
                },
              },
            ],
          }

          const myChart = echarts.init(document.getElementById('chartModule'))

          myChart.on('updateAxisPointer', function (event) {
            const xAxisInfo = event.axesInfo[0]
            if (xAxisInfo) {
              const dimension = xAxisInfo.value + 1
              myChart.setOption({
                series: {
                  id: 'pie',
                  label: {
                    formatter: '{b}: {@[' + dimension + ']} ({d}%)',
                  },
                  encode: {
                    value: dimension,
                    tooltip: dimension,
                  },
                },
              })
            }
          })

          myChart.setOption(option)
        }, 2000)
      },
    },
  }
</script>
