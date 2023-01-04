import grails.databinding.converters.ValueConverter;

class DoubleConverter implements ValueConverter {

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
            return new Double(value.replaceAll(",", "."))
        } else {
            return new Double(value)
        }
    }

    @Override
    Class<?> getTargetType() {
        return Double;
    }
}