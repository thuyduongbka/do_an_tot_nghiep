from googletrans import Translator
translator = Translator()
text = "USA "
print(translator.detect('이 문장은 한글로 쓰여졌습니다.'))
result = translator.translate(text, src='en', dest='vi')
print(result.text)
