package com.example.nice.everywhere.bean;

import java.util.List;

public class BanMiRouteBean {

    /**
     * code : 0
     * desc :
     * result : {"count":2,"page":1,"limit":20,"routes":[{"id":151,"cityID":37,"priceInCents":190,"title":"故宫","intro":"6小时故宫前朝中轴线","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1514537918440_94454fecd967547430c35ef76a9a62fb.jpg","videoURL":"","sequence":-849,"isPurchased":false,"isCollected":true,"city":"北京","price":"1.9","date":"2017-12-27 15:07"},{"id":144,"cityID":37,"priceInCents":190,"title":"故宫","intro":"5小时三宫六院甄嬛主题游","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1512719191506_98084e8c21d98d648959bdb75773f6a8.jpg","videoURL":"http://cdn.banmi.com/banmiapp/temp1213/144.mp4","sequence":-533,"isPurchased":false,"isCollected":false,"city":"北京","price":"1.9","date":"2017-12-07 09:54"}]}
     */

    private int code;
    private String desc;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * count : 2
         * page : 1
         * limit : 20
         * routes : [{"id":151,"cityID":37,"priceInCents":190,"title":"故宫","intro":"6小时故宫前朝中轴线","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1514537918440_94454fecd967547430c35ef76a9a62fb.jpg","videoURL":"","sequence":-849,"isPurchased":false,"isCollected":true,"city":"北京","price":"1.9","date":"2017-12-27 15:07"},{"id":144,"cityID":37,"priceInCents":190,"title":"故宫","intro":"5小时三宫六院甄嬛主题游","cardURL":"http://cdn.banmi.com/banmiapp/rahdna/1512719191506_98084e8c21d98d648959bdb75773f6a8.jpg","videoURL":"http://cdn.banmi.com/banmiapp/temp1213/144.mp4","sequence":-533,"isPurchased":false,"isCollected":false,"city":"北京","price":"1.9","date":"2017-12-07 09:54"}]
         */

        private int count;
        private int page;
        private int limit;
        private List<RoutesBean> routes;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<RoutesBean> getRoutes() {
            return routes;
        }

        public void setRoutes(List<RoutesBean> routes) {
            this.routes = routes;
        }

        public static class RoutesBean {
            /**
             * id : 151
             * cityID : 37
             * priceInCents : 190
             * title : 故宫
             * intro : 6小时故宫前朝中轴线
             * cardURL : http://cdn.banmi.com/banmiapp/rahdna/1514537918440_94454fecd967547430c35ef76a9a62fb.jpg
             * videoURL :
             * sequence : -849
             * isPurchased : false
             * isCollected : true
             * city : 北京
             * price : 1.9
             * date : 2017-12-27 15:07
             */

            private int id;
            private int cityID;
            private int priceInCents;
            private String title;
            private String intro;
            private String cardURL;
            private String videoURL;
            private int sequence;
            private boolean isPurchased;
            private boolean isCollected;
            private String city;
            private String price;
            private String date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCityID() {
                return cityID;
            }

            public void setCityID(int cityID) {
                this.cityID = cityID;
            }

            public int getPriceInCents() {
                return priceInCents;
            }

            public void setPriceInCents(int priceInCents) {
                this.priceInCents = priceInCents;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getCardURL() {
                return cardURL;
            }

            public void setCardURL(String cardURL) {
                this.cardURL = cardURL;
            }

            public String getVideoURL() {
                return videoURL;
            }

            public void setVideoURL(String videoURL) {
                this.videoURL = videoURL;
            }

            public int getSequence() {
                return sequence;
            }

            public void setSequence(int sequence) {
                this.sequence = sequence;
            }

            public boolean isIsPurchased() {
                return isPurchased;
            }

            public void setIsPurchased(boolean isPurchased) {
                this.isPurchased = isPurchased;
            }

            public boolean isIsCollected() {
                return isCollected;
            }

            public void setIsCollected(boolean isCollected) {
                this.isCollected = isCollected;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
