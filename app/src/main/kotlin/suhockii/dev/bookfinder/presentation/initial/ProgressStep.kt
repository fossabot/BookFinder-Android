package suhockii.dev.bookfinder.presentation.initial

enum class ProgressStep {
    LOADING {
        override val number: Int = 1
    },
    UNZIPPING {
        override val number: Int = 2
    },
    ANALYZING {
        override val number: Int = 3
    },
    PARSING {
        override val number: Int = 4
    },
    SAVING {
        override val number: Int = 5
    };

    abstract val number: Int
}