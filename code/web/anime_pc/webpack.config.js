const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const path = require('path');
const {host}=require("./build/proxy")
const page=require("./src/page")


const entry=()=>{
    let obj={}
    for(let key in page){
        obj[page[key]]="./src/static/js/"+page[key]+".js";
    }
    return obj
}
const template=()=>{
    let ary=[]
    for(let key in page){
        ary.push(getHtmlConfig(page[key],key))
    }
    return ary
}
//生成页面方法
const getHtmlConfig=(name,title)=>
    new HtmlWebpackPlugin({
        template:`./src/views/${name}.html`,
        title: title,
        filename: `${name}.html`,
        inject:true,
        hash:true,
        chunks: ["common",name]
    })
module.exports={
    mode: 'development',
    entry:entry,
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: './src/static/js/[name].[hash].js',
    },
    devServer: {
        contentBase: path.join(__dirname, 'dist'),
        compress: true,
        port: 9000,
        proxy: {
            '/api': {
                target: host,  //目标接口域名
                changeOrigin: true,  //是否跨域
                pathRewrite: {
                    '^/api': '/'   //重写接口
                }
            },
            cssSourceMap: false
        },
    },
    resolve: {
        // 设置别名
        alias: {
            '@': path.resolve('src')// 这样配置后 @ 可以指向 src 目录
        }
    },
    module:{
        rules: [
            {
                test: /\.css$/,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader,
                        options: {
                            publicPath: '../',
                            hmr: process.env.NODE_ENV === 'development',
                        },
                    },
                    'css-loader',
                ],
            },
            // {
            //     test: /\.css$/,
            //     // use: ["style-loader","css-loader"],
            //     use: ExtractTextPlugin.extract({
            //         fallback: "style-loader",
            //         use: [{
            //             loader: 'css-loader',
            //         }],
            //         publicPath: 'css'
            //     }),
            //
            // },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: [{
                    loader:"url-loader",
                    options:{
                        publicPath:"img",
                        name:"[name].[ext]",
                        outputPath: '/img',
                        limit: 8192
                    }
                }],
            },
            {
                test: /\.string$/,
                use: ["html-loader"],
            },
        ]
    },
    plugins: [
        // new ExtractTextPlugin("css/[name].css"),
        new MiniCssExtractPlugin({
            filename: 'css/[name].[hash].css',
            chunkFilename: '[id].css',
        }),
        new CleanWebpackPlugin(),
        ...template()
    ],
    optimization:{
        splitChunks:{
            cacheGroups: {
                commons: {
                    name : 'common',
                    chunks: "initial",
                    minChunks: 2
                }
            }
        }
    }
}
