from googletrans import Translator
translator = Translator()
text = "Honjo international awards for Graduate Students in Japan, 2020"
print(translator.detect('이 문장은 한글로 쓰여졌습니다.'))
result = translator.translate(text, src='en', dest='vi')
print(result.text)
