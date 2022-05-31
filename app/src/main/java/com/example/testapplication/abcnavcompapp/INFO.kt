package com.example.abcnavcomponent.abcnavcomp


//let apply also run with
//        val user = BFragment.User("user")
//
//        val a = user.apply {
//            name = "a"
//        }
//        val c = user.also {
//            it.name = "c"
//        }
//        val b = user.run {
//            name = "b"
//        }
//
//        val d = with(user) {
//            name = "d"
//        }
//        val e = user.let {
//            it.name = "e"
//        }
//        Log.e("######", a.toString())
//        Log.e("######", c.toString())
//        Log.e("######", b.toString())
//        Log.e("######", d.toString())
//        Log.e("######", e.toString())
//        print(a)
//        print(b)
//        print(c)
//        print(d)
//        print(e)


//@GET RETROFIT
//@GET("search")
//fun search(
//    @Query("numbers") numbers: List<String>
//): Call<ResponseBody>

//        val numbers = arrayListOf<String>()
//        numbers.add("1")
//        numbers.add("2")
//        numbers.add("3")
//        numbers.add("4")

//        api.search(numbers).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                Log.e("###", response.raw().toString())
//                Log.e("###", response.body().toString())
//                Log.e("###", response.code().toString())
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.e("###", "fail")
//            }
//        })


//RANDOM GET FROM LIST "NUMBER"
//fun random(int: Int) {
//    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//    val randomElements = list.shuffled().take(int)
//    Log.e("RANDOM", randomElements.toString())
//}
//
//fun random(number: Int) {
//    val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17)
//    val newList = mutableListOf<Any>()
//    var count = 0
//    while (true) {
//        val randomIndex = Random.nextInt(0, list.size)
//        newList.add(list[randomIndex])
//        list.removeAt(randomIndex)
//        count++
//        if (count == number) break
//    }
//    Log.e("RANDOM", newList.toString())
//    print(newList)
//}


//NAVCOMPONENT FROM C to A

//private lateinit var navController: NavController
//private lateinit var appBarConfiguration: AppBarConfiguration
//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//
//    val navHostFragment =
//        supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
//    navController = navHostFragment.findNavController()

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
//        val navController = navHostFragment.navController
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//override fun onBackPressed() {
//    when (navController.currentDestination?.id) {
//        R.id.CFragment -> navController.navigate(R.id.AFragment)
//        else -> super.onBackPressed()
//    }
//
//}
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragment_container_view)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
//ANIMATION NUMBERS
//fun TextView.animInteger(lastValue: Int) {
//    val animator = ValueAnimator.ofInt(0, lastValue)
//    animator.duration = 1000 // 5 seconds
//    animator.addUpdateListener { animation ->
//        text = animation.animatedValue.toString()
//    }
//    animator.start()
//}
// DISABLE BACK BTN
//requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//}