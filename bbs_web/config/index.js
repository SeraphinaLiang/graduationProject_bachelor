module.exports = {
  proxyTable: {
    '/': {
      target: 'http://localhost:8001', // 后端端口
      changeOrigin: true,
      pathRewrite: {
        '^/': '',
      },
    },
  },
}
