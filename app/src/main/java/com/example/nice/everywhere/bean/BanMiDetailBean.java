package com.example.nice.everywhere.bean;

import java.util.List;

public class BanMiDetailBean {

    /**
     * code : 0
     * desc :
     * result : {"share":{"shareTitle":"我是李炜，看看我的私藏旅行线路！","shareContent":"作为蜻蜓FM名嘴，知名文化记者，我的生活和旅行路线绝对精彩！","shareImage":"http://cdn.banmi.com/banmiapp/rahdna/1513046406045_cf228fb783b250c7897d65886200bdd3.jpg","shareURL":"http://banmi.com/app2017/banmi.html?id=28"},"banmi":{"id":28,"name":"李炜","location":"北京","occupation":"蜻蜓FM名嘴，知名文化记者","introduction":"你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。","following":4649,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1513046406045_cf228fb783b250c7897d65886200bdd3.jpg","isFollowed":true,"routesCount":2},"activities":[{"id":113,"content":"【专访故宫博物院院长 单霁翔】雨中故宫，时间仿佛都变慢了，单院说，文物的生命远比人要长，他们的职责就是延长这些文物的生命，保护好故宫的一切。往大说，要让故宫186万件文物尽可能的多跟大家见面，这是他的希望，往小做，经常穿着布鞋在故宫里捡纸片和烟头，也是他对故宫的情怀。谈谈侃侃，我越来越喜欢这位智慧而朴实的北京大爷了。","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1512718288453_1b416c7f8d034e00c2666c5b9bde1f0a.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718293626_9592240af9283b8403f48b652c54cf86.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718301471_1d47e24d29cc31cba8c81b916abd4044.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718319511_6ac83a4f6ba1b5e4395849b026335bd4.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718309294_909c922331dbcc45f7579824da0835f4.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718327265_b65b1d8b25b3c90a3aeb0fab309a6cb0.jpeg"],"firstImageWidth":0,"firstImageHeight":0,"likeCount":5,"replyCount":0,"isLiked":false,"date":"2017-12-08 15:32"}],"page":1,"limit":20,"count":1}
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
         * share : {"shareTitle":"我是李炜，看看我的私藏旅行线路！","shareContent":"作为蜻蜓FM名嘴，知名文化记者，我的生活和旅行路线绝对精彩！","shareImage":"http://cdn.banmi.com/banmiapp/rahdna/1513046406045_cf228fb783b250c7897d65886200bdd3.jpg","shareURL":"http://banmi.com/app2017/banmi.html?id=28"}
         * banmi : {"id":28,"name":"李炜","location":"北京","occupation":"蜻蜓FM名嘴，知名文化记者","introduction":"你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。","following":4649,"photo":"http://cdn.banmi.com/banmiapp/rahdna/1513046406045_cf228fb783b250c7897d65886200bdd3.jpg","isFollowed":true,"routesCount":2}
         * activities : [{"id":113,"content":"【专访故宫博物院院长 单霁翔】雨中故宫，时间仿佛都变慢了，单院说，文物的生命远比人要长，他们的职责就是延长这些文物的生命，保护好故宫的一切。往大说，要让故宫186万件文物尽可能的多跟大家见面，这是他的希望，往小做，经常穿着布鞋在故宫里捡纸片和烟头，也是他对故宫的情怀。谈谈侃侃，我越来越喜欢这位智慧而朴实的北京大爷了。","audioURL":"","audioLength":0,"images":["http://cdn.banmi.com/banmiapp/rahdna/1512718288453_1b416c7f8d034e00c2666c5b9bde1f0a.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718293626_9592240af9283b8403f48b652c54cf86.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718301471_1d47e24d29cc31cba8c81b916abd4044.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718319511_6ac83a4f6ba1b5e4395849b026335bd4.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718309294_909c922331dbcc45f7579824da0835f4.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718327265_b65b1d8b25b3c90a3aeb0fab309a6cb0.jpeg"],"firstImageWidth":0,"firstImageHeight":0,"likeCount":5,"replyCount":0,"isLiked":false,"date":"2017-12-08 15:32"}]
         * page : 1
         * limit : 20
         * count : 1
         */

        private ShareBean share;
        private BanmiBean banmi;
        private int page;
        private int limit;
        private int count;
        private List<ActivitiesBean> activities;

        public ShareBean getShare() {
            return share;
        }

        public void setShare(ShareBean share) {
            this.share = share;
        }

        public BanmiBean getBanmi() {
            return banmi;
        }

        public void setBanmi(BanmiBean banmi) {
            this.banmi = banmi;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ActivitiesBean> getActivities() {
            return activities;
        }

        public void setActivities(List<ActivitiesBean> activities) {
            this.activities = activities;
        }

        public static class ShareBean {
            /**
             * shareTitle : 我是李炜，看看我的私藏旅行线路！
             * shareContent : 作为蜻蜓FM名嘴，知名文化记者，我的生活和旅行路线绝对精彩！
             * shareImage : http://cdn.banmi.com/banmiapp/rahdna/1513046406045_cf228fb783b250c7897d65886200bdd3.jpg
             * shareURL : http://banmi.com/app2017/banmi.html?id=28
             */

            private String shareTitle;
            private String shareContent;
            private String shareImage;
            private String shareURL;

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getShareContent() {
                return shareContent;
            }

            public void setShareContent(String shareContent) {
                this.shareContent = shareContent;
            }

            public String getShareImage() {
                return shareImage;
            }

            public void setShareImage(String shareImage) {
                this.shareImage = shareImage;
            }

            public String getShareURL() {
                return shareURL;
            }

            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }
        }

        public static class BanmiBean {
            /**
             * id : 28
             * name : 李炜
             * location : 北京
             * occupation : 蜻蜓FM名嘴，知名文化记者
             * introduction : 你好，我是李炜，知名文化记者。闲来无事最喜欢与两三好友对坐闲侃，聊聊历史八卦、文化趣事。我向来讲求有枣没枣儿，先打三杆子，搂草打兔子，咱们讲哪算哪儿。我不是专家，不谈之乎者也洋洋洒洒。或沉土中千载，或睹世间沧桑穿朝越代，它们背后的故事远比宫斗精彩。浩渺的历史文化中，总有有趣的故事让人折服与感悟，拿出一点时间，我们一起去看世界之大。觉得有趣，图您一乐呵，万一不妥，请您多包涵。
             * following : 4649
             * photo : http://cdn.banmi.com/banmiapp/rahdna/1513046406045_cf228fb783b250c7897d65886200bdd3.jpg
             * isFollowed : true
             * routesCount : 2
             */

            private int id;
            private String name;
            private String location;
            private String occupation;
            private String introduction;
            private int following;
            private String photo;
            private boolean isFollowed;
            private int routesCount;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public int getFollowing() {
                return following;
            }

            public void setFollowing(int following) {
                this.following = following;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public boolean isIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(boolean isFollowed) {
                this.isFollowed = isFollowed;
            }

            public int getRoutesCount() {
                return routesCount;
            }

            public void setRoutesCount(int routesCount) {
                this.routesCount = routesCount;
            }
        }

        public static class ActivitiesBean {
            /**
             * id : 113
             * content : 【专访故宫博物院院长 单霁翔】雨中故宫，时间仿佛都变慢了，单院说，文物的生命远比人要长，他们的职责就是延长这些文物的生命，保护好故宫的一切。往大说，要让故宫186万件文物尽可能的多跟大家见面，这是他的希望，往小做，经常穿着布鞋在故宫里捡纸片和烟头，也是他对故宫的情怀。谈谈侃侃，我越来越喜欢这位智慧而朴实的北京大爷了。
             * audioURL :
             * audioLength : 0
             * images : ["http://cdn.banmi.com/banmiapp/rahdna/1512718288453_1b416c7f8d034e00c2666c5b9bde1f0a.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718293626_9592240af9283b8403f48b652c54cf86.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718301471_1d47e24d29cc31cba8c81b916abd4044.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718319511_6ac83a4f6ba1b5e4395849b026335bd4.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718309294_909c922331dbcc45f7579824da0835f4.jpeg","http://cdn.banmi.com/banmiapp/rahdna/1512718327265_b65b1d8b25b3c90a3aeb0fab309a6cb0.jpeg"]
             * firstImageWidth : 0
             * firstImageHeight : 0
             * likeCount : 5
             * replyCount : 0
             * isLiked : false
             * date : 2017-12-08 15:32
             */

            private int id;
            private String content;
            private String audioURL;
            private int audioLength;
            private int firstImageWidth;
            private int firstImageHeight;
            private int likeCount;
            private int replyCount;
            private boolean isLiked;
            private String date;
            private List<String> images;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAudioURL() {
                return audioURL;
            }

            public void setAudioURL(String audioURL) {
                this.audioURL = audioURL;
            }

            public int getAudioLength() {
                return audioLength;
            }

            public void setAudioLength(int audioLength) {
                this.audioLength = audioLength;
            }

            public int getFirstImageWidth() {
                return firstImageWidth;
            }

            public void setFirstImageWidth(int firstImageWidth) {
                this.firstImageWidth = firstImageWidth;
            }

            public int getFirstImageHeight() {
                return firstImageHeight;
            }

            public void setFirstImageHeight(int firstImageHeight) {
                this.firstImageHeight = firstImageHeight;
            }

            public int getLikeCount() {
                return likeCount;
            }

            public void setLikeCount(int likeCount) {
                this.likeCount = likeCount;
            }

            public int getReplyCount() {
                return replyCount;
            }

            public void setReplyCount(int replyCount) {
                this.replyCount = replyCount;
            }

            public boolean isIsLiked() {
                return isLiked;
            }

            public void setIsLiked(boolean isLiked) {
                this.isLiked = isLiked;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }
}
