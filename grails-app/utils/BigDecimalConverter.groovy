import grails.databinding.converters.ValueConverter;

class BigDecimalConverter implements ValueConverter {

    @Override
    boolean canConvert(Object value) {
        value instanceof String
    }

    @Override
    Object convert(Object value) {
        if (value == null) {
            return null
        }
        if (value != null && value.trim() == '') {
            return null
        }
        if (value?.contains(',')) {
            return new BigDecimal(value.replaceAll(",", "."))
        } else {
            return new BigDecimal(value)
        }
    }

    @Override
    Class<?> getTargetType() {
        return BigDecimal;
    }
}