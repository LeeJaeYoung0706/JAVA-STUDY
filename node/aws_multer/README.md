AWS_S3 REST API 구성 공부 및 정리
Config code
AWS.config.update({
    accessKeyId: process.env.S3_ACCESS_KEY_ID,
    secretAccessKey: process.env.S3_SECRET_ACCESS_KEY,
    region: 'ap-northeast-2',
});
S3 에 접근하기 위한 Config 설정 코드 입니다.

const AWSUpload = multer({
    storage: multerS3({
        // 저장 위치
        s3: new AWS.S3(),
        bucket: 'test-video',
        acl: "public-read",
        contentType: multerS3.AUTO_CONTENT_TYPE,
        key(req, file, cb) {
            const ext = path.extname(file.originalname);
            const idx = req.body.idx;
            cb(null, dayjs(Date.now()).format("YYYYMMDD") + '/' + idx + '/' + path.basename(file.originalname , ext) + ext); // 저장 될 파일 명
        }
    }),
    // 파일 Filter ( REST API Filter와 같은 역할 )
    fileFilter: async function (req, file, cb) {
        const param = req.body;
        // 유효성 검증
        if(param != null) {
            if (param.idx == null){
                req.paramiterError = "입력되지 않은 파일 정보입니다. 요청할 수 없습니다.";
                return cb(null, false , new Error("입력되지 않은 파일 정보입니다. 요청할 수 없습니다."))
            }
            const result = await videoFileService.videoFileExistParameter(param.idx);
            if (!result){
                req.paramiterError = "파일 파라미터 정보가 존재하지 않습니다. 파일 업로드를 진행할 수 없습니다.";
                return cb(null, false , new Error("파일 파라미터 정보가 존재하지 않습니다. 파일 업로드를 진행할 수 없습니다."))
            }

            const resultCount = await videoFileService.videoFileCount(param.idx);
            if (resultCount){
                req.paramiterError = "이미 같은 파라미터 정보를 가진 파일이 업로드 되어 있습니다.";
                return cb(null, false , new Error("이미 같은 파라미터 정보를 가진 파일이 업로드 되어 있습니다."))
            }

            cb(null, true)
        } else {
            return cb(null , false)
        }
    }
}).single("file");
파일 업로드 시 처리

스웨거를 통해 REST API 요청 테스트 및 파라미터에 대한 정보를 얻을 수 있도록 하였습니다.
{
  "swagger": "2.0",
  "info": {
    "title": "테스트 API",
    "version": "1.0.0",
    "description": "테스트 API"
  },
  "host": "localhost:8080",
  "basePath": "/",
  "schemes": [
    "http"
  ],
  "securityDefinitions": {
    "device_key": {
      "type": "apiKey",
      "name": "device_key",
      "in": "header"
    }
  },
  "paths": {
    "/video/key_issue/request": {
      "post": {
        "tags": [
          "비디오 인증 키 발급 테스트"
        ],
        "summary": "비디오 인증 키 발급 테스트",
        "description": "",
        "consumes": [
          "multipart/form-data"
        ],
        "parameters": [
          {
            "name": "msisdn",
            "in": "formData",
            "description": "단말기번호",
            "type": "string"
          }
        ],
        "responses": {
          "200": {
            "description": "응답",
            "schema": {
              "type": "object",
              "properties": {
                "code": {
                  "type": "string",
                  "example": "200"
                },
                "message": {
                  "type": "string",
                  "example": "성공"
                },
                "data": {
                  "type": "object",
                  "properties": {
                    "key": {
                      "type": "string",
                      "example": "key"
                    }
                  }
                }
              },
              "xml": {
                "name": "main"
              }
            }
          }
        }
      }
    },
    "/video/video_file/insert": {
      "post": {
        "tags": [
          "비디오 파일 테스트"
        ],
        "summary": "비디오 파일 테스트",
        "description": "",
        "consumes": [
          "multipart/form-data"
        ],
        "parameters": [
          {
            "name": "idx",
            "in": "formData",
            "description": "파일저장번호",
            "type": "string"
          },
          {
            "name": "file",
            "in": "formData",
            "description": "videoFile",
            "type": "file"
          }
        ],
        "responses": {
          "200": {
            "description": "응답",
            "schema": {
              "type": "object",
              "properties": {
                "code": {
                  "type": "string",
                  "example": "200"
                },
                "message": {
                  "type": "string",
                  "example": "성공"
                },
                "data": {
                  "type": "object",
                  "properties": {}
                }
              },
              "xml": {
                "name": "main"
              }
            }
          }
        },
        "security": [
          {
            "device_key": []
          }
        ]
      }
    },
    "/video/video_parameter/request": {
      "post": {
        "tags": [
          "비디오 파라미터 받기 테스트"
        ],
        "summary": "비디오 파라미터 받기 테스트",
        "description": "",
        "consumes": [
          "multipart/form-data"
        ],
        "parameters": [
          {
            "name": "msisdn",
            "in": "formData",
            "description": "단말기번호",
            "type": "string"
          },
          {
            "name": "occurrence_date",
            "in": "formData",
            "description": "발생 일자",
            "type": "string"
          },
          {
            "name": "lang",
            "in": "formData",
            "description": "X 축",
            "type": "string"
          },
          {
            "name": "lat",
            "in": "formData",
            "description": "Y 축",
            "type": "string"
          },
          {
            "name": "video_type",
            "in": "formData",
            "description": "비디오 타입",
            "type": "string"
          }
        ],
        "responses": {
          "200": {
            "description": "응답",
            "schema": {
              "type": "object",
              "properties": {
                "code": {
                  "type": "string",
                  "example": "200"
                },
                "message": {
                  "type": "string",
                  "example": "성공"
                },
                "data": {
                  "type": "object",
                  "properties": {
                    "idx": {
                      "type": "string",
                      "example": "file 저장 인덱스"
                    }
                  }
                }
              },
              "xml": {
                "name": "main"
              }
            }
          }
        },
        "security": [
          {
            "device_key": []
          }
        ]
      }
    }
  }
}
